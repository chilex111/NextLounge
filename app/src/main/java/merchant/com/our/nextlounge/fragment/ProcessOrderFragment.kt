package merchant.com.our.nextlounge.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.dialog_payment_card.*

import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.adapter.ProcessAdapter
import merchant.com.our.nextlounge.db.LocalDatabase
import merchant.com.our.nextlounge.model.PriceModel
import java.text.DecimalFormat
import android.widget.Toast
import android.widget.RadioGroup
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.PaystackSdk.chargeCard
import co.paystack.android.Transaction
import co.paystack.android.exceptions.ExpiredAccessCodeException
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.google.gson.Gson
import fewchore.com.exolve.fewchore.helper.CreditCardFormatter
import kotlinx.android.synthetic.main.fragment_process_order.*
import merchant.com.our.nextlounge.enums.CardValidity
import merchant.com.our.nextlounge.helper.AppUtils
import merchant.com.our.nextlounge.helper.HttpUtility
import merchant.com.our.nextlounge.listener.PayStackCardValidationListener
import merchant.com.our.nextlounge.model.PayStackModel
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TABLE_NO = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProcessOrderFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProcessOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProcessOrderFragment : Fragment(),PayStackCardValidationListener {
    // TODO: Rename and change types of parameters
    private var tableNo: String? = null
    private var param2: String? = null
    // private var listener: OnFragmentInteractionListener? = null
    private var recyclerView :RecyclerView ?= null
    private var buttonpay :Button ?= null
    private var printBill :Button ?= null
    private var textAmt :TextView ?= null
    private var textTableNo :TextView ?= null
    private var empty :RelativeLayout ?= null
    private var localDB :LocalDatabase ?= null
    private var totalPrice: Double?= null
    private var cvvIsValid = false
    private var cardIsValid = false
    private var card: Card? = null
    private var charge: Charge? = null
    private var expiryDateIsValid = false
    private var mButtonPerformTransaction: Button? = null
    private var mEditCVC: EditText? = null
    private var mEditCardNum: EditText? = null
    private var mEditExpiryDate: EditText? = null
    private var relativeProgress : RelativeLayout ?= null
    private var mSubPrice: TextView? = null
    private var diag: Dialog? = null
    private var progressDialog: ProgressDialog? = null
    private var appUtils: AppUtils ?= null
    var transactions: Transaction? = null
    private var dialog: Dialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tableNo = it.getString(TABLE_NO)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_process_order, container, false)

        localDB = LocalDatabase(activity!!)
        appUtils = AppUtils(activity!!)

        textAmt = v.findViewById(R.id.textTotalAmount)
        textTableNo = v.findViewById(R.id.textTableNo)
        buttonpay = v.findViewById(R.id.buttonPay)
        printBill = v.findViewById(R.id.buttonPrintBill)
        recyclerView = v.findViewById(R.id.recyclerView)
        empty = v.findViewById(R.id.relativeEmpty)
        relativeProgress = v.findViewById(R.id.relativeProgress)

        textTableNo!!.text = tableNo

        if (!localDB!!.allOrder.isEmpty() &&(localDB != null)){
            val adapter = ProcessAdapter(localDB!!.allOrder, this.activity!!)
            recyclerView!!.layoutManager = LinearLayoutManager(activity)
            recyclerView!!.adapter = adapter
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.requestFocus()

            val priceList = localDB!!.allPrice
            totalPrice = getTotal(priceList)

            val decimalFormat = DecimalFormat("#,###.00")
            val am = decimalFormat.format(java.lang.Double.parseDouble(totalPrice.toString()))
            textAmt!!.text = am
        }
        else{
            empty!!.visibility = View.VISIBLE
        }


        v.findViewById<ImageButton>(R.id.buttonBack).setOnClickListener {
            activity!!.onBackPressed()
        }
        buttonpay!!.setOnClickListener {
            payNow()
        }
        return  v
    }

    private fun payNow() {
        dialog = Dialog(activity)
        dialog!!.setContentView(R.layout.dialog_payment_card)
        val totalAmt = dialog!!.findViewById<TextView>(R.id.textTotalAmount)
        totalAmt.text = totalPrice.toString()
        val checkPrint = dialog!!.findViewById<CheckBox>(R.id.checkBoxPrint)
        val radioGroup = dialog!!.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, i ->
            if (i == R.id.radioCash) {
                val radio:RadioButton = dialog!!.findViewById(i)

                Toast.makeText(activity, "choice:"+radio.text.toString(),
                        Toast.LENGTH_SHORT).show()
            } else if (i == R.id.radioCard) {
                val radio:RadioButton = dialog!!.findViewById(i)

                Toast.makeText(activity, "choice: "+radio.text.toString(),
                        Toast.LENGTH_SHORT).show()
            }
        }

        dialog!!.findViewById<Button>(R.id.buttonPayment).setOnClickListener {
            val i = radioGroup.checkedRadioButtonId
            val radio:RadioButton = dialog!!.findViewById(i)
            if (radio.text.toString() =="Card payment")
                initPayStack()

            Toast.makeText(activity, radio.text.toString(), Toast.LENGTH_LONG).show()
        }
        dialog!!.show()
    }

    private fun initPayStack() {
        val paystack_public_key = "pk_test_aaf26352dbb0e390d861af8bf3909616045b251b"
        PaystackSdk.setPublicKey(paystack_public_key)
        PaystackSdk.initialize(activity)
        showCardDialog(totalPrice.toString())
    }

    private fun showCardDialog(amount: String) {
        cardIsValid = false
        expiryDateIsValid = false
        cvvIsValid = false
        val builder = AlertDialog.Builder(activity)

        val inflater = (activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?)!!
        @SuppressLint("InflateParams")
        val dialogView = inflater.inflate(R.layout.dialog_card_detail, null)

        mEditCardNum = dialogView.findViewById(R.id.edit_card_number)
        mEditCVC = dialogView.findViewById(R.id.edit_cvc)
        mSubPrice = dialogView.findViewById(R.id.sub_price)
        mEditExpiryDate = dialogView.findViewById(R.id.edit_expiry_date)

        mButtonPerformTransaction = dialogView.findViewById(R.id.button_perform_transaction)
        mButtonPerformTransaction!!.isEnabled = false
        @SuppressLint("SimpleDateFormat")
        val nowAsString = SimpleDateFormat("yy").format(Date())
        @SuppressLint("SimpleDateFormat")
        val merchantRef = "NextLounge" + nowAsString + SimpleDateFormat("yyyyMMddHHmmss").format(Date())

        mSubPrice!!.text = amount
        mEditCardNum!!.addTextChangedListener(CreditCardFormatter(CardValidity.CARD_NO, this, mEditExpiryDate!!, card, 16))
        mButtonPerformTransaction!!.setOnClickListener {
            validateCardForm()
            if (card != null && card!!.isValid) {
                progressDialog = ProgressDialog(activity)
                progressDialog!!.setMessage("Performing transaction... please wait")
                progressDialog!!.setCancelable(false)
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.show()
                diag!!.dismiss()

                try {
                    startAFreshCharge(merchantRef)
                } catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                    progressDialog!!.dismiss()
                }

            }
        }
        builder.setView(dialogView)
        diag = builder.create()
        diag!!.show()
    }

    private fun startAFreshCharge(paramString2: String) {
        if (appUtils!!.hasActiveInternetConnection(activity!!)) {
            diag!!.dismiss()
            charge = Charge()
            val i = Integer.parseInt(totalPrice.toString())
            charge!!.reference = paramString2
            val email = AppUtils.getMyEmail(activity)
            charge!!.email = email
            charge!!.amount = i * 100
            charge!!.card = this.card
            chargeCard()

        } else {
            appUtils!!.showAlert("Internet connection required")
        }
    }

    private fun chargeCard() {

        PaystackSdk.chargeCard(activity, charge, object : Paystack.TransactionCallback {
            override fun beforeValidate(transaction: Transaction) {
                transactions = transaction
            }


            override fun onError(error: Throwable, transaction: Transaction) {
                transactions = transaction
                if (error is ExpiredAccessCodeException) {
                    @SuppressLint("SimpleDateFormat")
                    val nowAsString = SimpleDateFormat("yy").format(Date())
                    @SuppressLint("SimpleDateFormat")
                    val merchantRef = "NextLounge" + nowAsString + SimpleDateFormat("yyyyMMddHHmmss").format(Date())

                    startAFreshCharge(merchantRef)
                    chargeCard()
                    return
                }
                dismissDialog()
                if (transaction.reference != null) {
                    Toast.makeText(activity, transaction.reference, Toast.LENGTH_LONG).show()
                    if (appUtils!!.hasActiveInternetConnection(activity!!)) {
                        val verifyUrl = "https://api.paystack.co/transaction/verify/" + transaction.reference
                        relativeProgress!!.visibility = View.VISIBLE
                        VerifyOnServer(verifyUrl).execute()
                        return
                    }else
                        appUtils!!.showAlert("Internet connection required")
                    return
                }
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onSuccess(transaction: Transaction) {
                dismissDialog()
                transactions = transaction
                Log.i("ChargeCard_Success", transactions!!.reference + " Successful")
                val url = "https://api.paystack.co/transaction/verify/" + transactions!!.reference
                if (appUtils!!.hasActiveInternetConnection(activity!!)) {
                    relativeProgress!!.visibility = View.VISIBLE
                    VerifyOnServer(url).execute()

                }
                appUtils!!.showAlert("Internet connection required")
            }
        })
    }

    private fun dismissDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    private fun  getTotal(models: List<PriceModel>): Double {
        val listPrice: MutableList<Int> = ArrayList()
        for (valuePrice in models) {
            listPrice.add(valuePrice.amount!!)
        }
        var sum = 0.0

        for (i in listPrice.indices)
            sum += listPrice[i].toDouble()
        return sum
    }
    private fun validateCardForm() {
        //validate fields
        val cardNum = mEditCardNum!!.text.toString().trim().replace(" ", "")

        if (TextUtils.isEmpty(cardNum)) {
            mEditCardNum!!.error = "Empty card number"
            return
        }

        //build card object with ONLY the number, update the other fields later
        val card = Card.Builder(cardNum, 0, 0, "").build()
        if (!card.validNumber()) {
            mEditCardNum!!.error = "Invalid card number"
            return
        }

        //validate cvc
        val cvc = mEditCVC!!.text.toString().trim()
        if (TextUtils.isEmpty(cvc)) {
            mEditCVC!!.error = "Empty cvc"
            return
        }
        //update the cvc field of the card
        card.cvc = cvc

        //check that it's valid
        if (!card.validCVC()) {
            mEditCVC!!.error = "Invalid cvc"
            return
        }

        val date = mEditExpiryDate!!.text.toString()
        if (date.contains("/")) {
            val rawYear = Calendar.getInstance().get(Calendar.YEAR).toString()
            val yearPrefix = rawYear.substring(0, 2)
            val monthYear = date.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val monthStr = monthYear[0]
            val yearStr = yearPrefix + monthYear[1]

            var month = -1
            try {
                month = Integer.parseInt(monthStr)
            } catch (ignored: Exception) {
            }

            if (month in 1..12) {
                card.expiryMonth = month
            } else {
                return
            }

            var year = -1
            try {
                year = Integer.parseInt(yearStr)
            } catch (ignored: Exception) {
            }

            if (year > 0) {
                card.expiryYear = year
            } else {
                return
            }

            if (!card.validExpiryDate()) {
                mEditExpiryDate!!.error = "Invalid expiry"
            }
        }
    }

    override fun afterChange(cardValidity: CardValidity, editable: Editable) {
        when (cardValidity) {
            CardValidity.EXPIRY_DATE -> {
                if (editable.length == 2) {
                    var month = -1
                    try {
                        month = Integer.parseInt(editable.toString().trim { it <= ' ' })
                    } catch (e: Exception) {
                    }

                    if (month < 1 || month > 12) {
                        mEditExpiryDate!!.error = "Invalid month"
                    }
                    if (editable.length == 5 && !editable.toString().contains("/")) {
                        mEditExpiryDate!!.error = "Invalid date"
                        return
                    }
                    return
                }
                return
            }
            else -> {
            }
        }

    }

    override fun paramIsValid(z: Boolean, cardValidity: CardValidity) {
        when (cardValidity) {
            CardValidity.CARD_NO -> {
                if (z) {
                    card = Card.Builder(mEditCardNum!!.text.toString().trim().replace(" ", ""), Integer.valueOf(0), Integer.valueOf(0), "").build()
                    mEditExpiryDate!!.addTextChangedListener(CreditCardFormatter(CardValidity.EXPIRY_DATE, this, mEditCVC!!, card, 5))
                    mEditCVC!!.addTextChangedListener(CreditCardFormatter(CardValidity.CVV, this, mEditCardNum!!, card, 3))
                    mEditExpiryDate!!.visibility = View.VISIBLE
                    mEditCVC!!.visibility = View.VISIBLE
                    mButtonPerformTransaction!!.visibility = View.VISIBLE
                    cardIsValid = true
                    checkCardValidity()
                    return
                }
                mEditCardNum!!.error = "Invalid card number"
                return
            }
            CardValidity.EXPIRY_DATE -> {
                if (z) {
                    expiryDateIsValid = true
                    checkCardValidity()
                    return
                }
                mEditExpiryDate!!.error = "Invalid expiry date"
                return
            }
            CardValidity.CVV -> {
                if (z) {
                    cvvIsValid = true
                    checkCardValidity()
                    return
                }
                mEditCVC!!.error = "Invalid cvc"
                return
            }
            else -> return
        }
    }


    private fun checkCardValidity() {
        if (this.cardIsValid && expiryDateIsValid && cvvIsValid) {
            mButtonPerformTransaction!!.isEnabled = true
        }
    }


    @SuppressLint("StaticFieldLeak")
    private inner class VerifyOnServer internal constructor(private val verifyTokenUrl: String) : AsyncTask<Void, Int, String>() {

        override fun doInBackground(vararg voids: Void): String? {
            try {
                return HttpUtility.getPayStackRequest(verifyTokenUrl)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            relativeProgress!!.visibility = View.GONE
            if (result != null) {
                val gson = Gson()
                try {
                    val payStackModel = gson.fromJson<PayStackModel>(result, PayStackModel::class.java)
                    if (payStackModel.status) {

                        val amountPaid = payStackModel.data.amount
                        val amount = amountPaid / 100
                        dismissDialog()
                        buttonpay!!.visibility = View.GONE
                        buttonPrintBill.visibility = View.VISIBLE
                        //SubmitPayAsync(amount, forWho).execute()


                    } else
                        appUtils!!.showAlert(payStackModel.message)
                } catch (e: Exception) {
                    //  Crashlytics.log(e.getMessage());
                }

            } else
                appUtils!!.showAlert("Please try again later... Payment was not successful")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param table_No Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProcessOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(table_No: String?, param2: String?) =
                ProcessOrderFragment().apply {
                    arguments = Bundle().apply {
                        putString(TABLE_NO, table_No)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}

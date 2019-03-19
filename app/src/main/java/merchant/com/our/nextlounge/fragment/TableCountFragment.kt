package merchant.com.our.nextlounge.fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.helper.AppUtils
import merchant.com.our.nextlounge.helper.Const
import merchant.com.our.nextlounge.helper.HttpUtility
import merchant.com.our.nextlounge.listener.StringListener
import merchant.com.our.nextlounge.model.TableModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TableCountFragment : Fragment(),View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var table1 : TextView ?= null
    private var table2 : TextView ?= null
    private var table3 : TextView ?= null
    private var table4 : TextView ?= null
    private var table5 : TextView ?= null
    private var table6 : TextView ?= null
    private var table7 : TextView ?= null
    private var table8 : TextView ?= null
    private var table9 : TextView ?= null
    private var table10 : TextView ?= null
    private var table11 : TextView ?= null
    private var table12 : TextView ?= null
    private var table13 : TextView ?= null
    private var table14 : TextView ?= null
    private var table15 : TextView ?= null
    private var table16 : TextView ?= null
    private var table17 : TextView ?= null
    private var table18 : TextView ?= null
    private var table19 : TextView ?= null
    private var table20 : TextView ?= null
    private var selectedTable : String ?= null
    private var tableList : MutableList<TextView> ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_table_count, container, false)
        table1 = v.findViewById(R.id.table1)
        table2 = v.findViewById(R.id.table2)
        table3 = v.findViewById(R.id.table3)
        table4 = v.findViewById(R.id.table5)
        table5 = v.findViewById(R.id.table5)
        table6 = v.findViewById(R.id.table6)
        table7 = v.findViewById(R.id.table7)
        table8 = v.findViewById(R.id.table8)
        table9 = v.findViewById(R.id.table9)
        table10 = v.findViewById(R.id.table10)
        table11 = v.findViewById(R.id.table11)
        table12 = v.findViewById(R.id.table12)
        table13 = v.findViewById(R.id.table13)
        table14 = v.findViewById(R.id.table14)
        table15 = v.findViewById(R.id.table15)
        table16 = v.findViewById(R.id.table16)
        table17 = v.findViewById(R.id.table17)
        table18 = v.findViewById(R.id.table18)
        table19 = v.findViewById(R.id.table19)
        table20 = v.findViewById(R.id.table20)

        tableList = ArrayList()
        tableList!!.add(table1!!)
        tableList!!.add(table2!!)
        tableList!!.add(table3!!)
        tableList!!.add(table4!!)
        tableList!!.add(table5!!)
        tableList!!.add(table6!!)
        tableList!!.add(table7!!)
        tableList!!.add(table8!!)
        tableList!!.add(table9!!)
        tableList!!.add(table10!!)
        tableList!!.add(table11!!)
        tableList!!.add(table12!!)
        tableList!!.add(table13!!)
        tableList!!.add(table14!!)
        tableList!!.add(table15!!)
        tableList!!.add(table16!!)
        tableList!!.add(table17!!)
        tableList!!.add(table18!!)
        tableList!!.add(table19!!)
        tableList!!.add(table20!!)

        TableAsync().execute()

        table1!!.setOnClickListener(this)
        table2!!.setOnClickListener(this)
        table3!!.setOnClickListener(this)
        table4!!.setOnClickListener(this)
        table5!!.setOnClickListener(this)
        table6!!.setOnClickListener(this)
        table7!!.setOnClickListener(this)
        table8!!.setOnClickListener(this)
        table9!!.setOnClickListener(this)
        table10!!.setOnClickListener(this)
        table11!!.setOnClickListener(this)
        table12!!.setOnClickListener(this)
        table13!!.setOnClickListener(this)
        table14!!.setOnClickListener(this)
        table15!!.setOnClickListener(this)
        table16!!.setOnClickListener(this)
        table17!!.setOnClickListener(this)
        table18!!.setOnClickListener(this)
        table19!!.setOnClickListener(this)
        table20!!.setOnClickListener(this)

        v.findViewById<ImageButton>(R.id.buttonBack).setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        return v
    }
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.table1 ->{
                selectedTable = "1"
            }
            R.id.table2 ->{
                selectedTable = "2"

            }
            R.id.table3 ->{
                selectedTable = "3"

            }
            R.id.table4 ->{
                selectedTable = "4"

            }
            R.id.table5 ->{
                selectedTable = "5"

            }
            R.id.table6 ->{
                selectedTable = "6"

            }
            R.id.table7 ->{
                selectedTable = "7"

            }
            R.id.table8 ->{
                selectedTable = "8"

            }
            R.id.table9 ->{
                selectedTable = "9"

            }
            R.id.table10 ->{
                selectedTable = "10"

            }
            R.id.table11->{
                selectedTable = "11"

            }
            R.id.table12 ->{
                selectedTable = "12"

            }
            R.id.table13 ->{
                selectedTable = "13"

            }
            R.id.table14 ->{
                selectedTable = "14"

            }
            R.id.table15 ->{
                selectedTable = "15"

            }
            R.id.table16 ->{
                selectedTable = "16"

            }
            R.id.table17 ->{
                selectedTable = "17"

            }
            R.id.table18 ->{
                selectedTable = "18"

            }
            R.id.table19 ->{
                selectedTable = "19"

            }
            R.id.table20 ->{
                selectedTable = "20"
            }

        }
        Toast.makeText(activity, "You selected table $selectedTable", Toast.LENGTH_LONG).show()
        stringListener!!.stringListener(selectedTable!!)

    }

    @SuppressLint("StaticFieldLeak")
    inner class TableAsync:AsyncTask<Void,Int,String>(){
        override fun doInBackground(vararg p0: Void?): String {
            val token = AppUtils.getMyToken(activity)
            val url = Const.NEXT_URL+"tables"
            return HttpUtility.getRequest(url,token)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!result.isNullOrEmpty() ){
                val gson = Gson()
                val type= object : TypeToken<TableModel>(){}.type
                val tableModel = gson.fromJson<TableModel>(result, type)
                if (tableModel.status =="Successful"){
                    for( table in tableModel.tables){
                        if (table.status =="Unavailable"){
                            var tableNo: String?
                            val tableCheck = table.tableNo.toString()
                            if (tableCheck.length ==2)
                                tableNo = table.tableNo.toString()
                            else
                                tableNo  ="0"+ table.tableNo
                            for (singleTable in tableList!!){
                                val value = singleTable.text.toString()
                                if (tableNo == value){
                                    singleTable.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.orange))
                                 //   Toast.makeText(activity, "This table is already Booked", Toast.LENGTH_LONG).show()
                                    singleTable.isEnabled = false

                                }
                            }

                        }
                    }
                }
            }
        }

    }

    companion object {
        var stringListener: StringListener ?= null
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TableCountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TableCountFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}

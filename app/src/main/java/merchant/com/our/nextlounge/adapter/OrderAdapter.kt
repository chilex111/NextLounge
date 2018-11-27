package merchant.com.our.nextlounge.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.model.OrderModel

class OrderAdapter(private val cardModelList: List<OrderModel>?, private val context: Context)
    : RecyclerView.Adapter<OrderAdapter.CardViewHolder>() {

    companion object {
        var cardPosition: Int? = -1
        @SuppressLint("StaticFieldLeak")
        var viewHolder: CardViewHolder? = null

    }


    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     //   var cardView: CardView = itemView.findViewById(R.id.container)
        var textFood: TextView ?= null
        val textExpiry:TextView
        val textCVV: TextView
        //   val cardType : TextView


        init {
            this.textFood = itemView.findViewById(R.id.textFood)
            this.textExpiry = itemView.findViewById(R.id.textFoodDescript)
            this.textCVV = itemView.findViewById(R.id.textPrice)
            // cardType = itemView.findViewById(R.id.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val v = inflater.inflate(R.layout.custom_new_order, parent, false)
        return CardViewHolder(v)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        viewHolder = holder
/*

        val cardNum = "**** **** **** "+cardModelList[position].cardNo
        val cardCVV =  "***"
        val cardExp =  cardModelList[position].cardExpiry
        val cardId = cardModelList[position].cardId
        val card_type = cardModelList[position].cardType
        val auth_code = cardModelList[position].authCode

        holder.foodName!!.text = cardNum
        holder.price.text =cardCVV
        holder.foodDescribed.text= cardExp
        holder.cardType.text = card_type


        try {
            holder.cardView.setOnClickListener {
                if (pageValue =="2"){

                }else {
                    cardPosition = position
                    notifyDataSetChanged()

                    if (cardPosition == position) {
                        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
                        stringlistener!!.accountDetailsListener(cardNum, cardExp, cardId, "ADD", auth_code)
                    } else {
                        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    }
                }
            }
        }catch (e: Exception){
            Log.i("CARD_TAG", e.message)
        }
*/

    }

    override fun getItemCount(): Int {
        return 6
    }


}


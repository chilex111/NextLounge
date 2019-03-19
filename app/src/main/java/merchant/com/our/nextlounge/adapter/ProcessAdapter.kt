package merchant.com.our.nextlounge.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.model.OrderModel

class ProcessAdapter(private val processModelist: List<OrderModel>?, private val context: Context)
    : RecyclerView.Adapter<ProcessAdapter.CardViewHolder>() {

    companion object {
        var cardPosition: Int? = -1
        @SuppressLint("StaticFieldLeak")
        var viewHolder: CardViewHolder? = null

    }


    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //   var cardView: CardView = itemView.findViewById(R.id.container)
        val textFood: TextView = itemView.findViewById(R.id.textFood)
        val textDesc:TextView = itemView.findViewById(R.id.textFoodDescript)
        val textAmt: TextView = itemView.findViewById(R.id.textPrice)
        val image : ImageView = itemView.findViewById(R.id.imageFood)
        val textQty : TextView = itemView.findViewById(R.id.textQuantity)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val v = inflater.inflate(R.layout.custom_order_process, parent, false)
        return CardViewHolder(v)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.textDesc.text = processModelist!![position].food_description
        holder.textAmt.text =processModelist[position].amount
        holder.textQty.text = processModelist[position].quantity
        holder.textFood.text =processModelist[position].foodName
        if (!processModelist[position].image.isNullOrEmpty())
            Picasso.with(context).load(processModelist[position].image).into(holder.image)
        else{
                holder.image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.cola_glass1))

        }
/*

        val cardNum = "**** **** **** "+processModelist[position].cardNo
        val cardCVV =  "***"
        val cardExp =  processModelist[position].cardExpiry
        val cardId = processModelist[position].cardId
        val card_type = processModelist[position].cardType
        val auth_code = processModelist[position].authCode

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
        return processModelist!!.size
    }


}


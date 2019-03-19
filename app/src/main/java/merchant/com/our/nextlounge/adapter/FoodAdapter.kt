package merchant.com.our.nextlounge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import merchant.com.our.nextlounge.R
import merchant.com.our.nextlounge.listener.MenuListener
import merchant.com.our.nextlounge.model.Menu
import merchant.com.our.nextlounge.model.OrderModel

class FoodAdapter(private val modelList: List<Menu>?, private val context: Context)
    : RecyclerView.Adapter<FoodAdapter.MenuViewHolder>() {

    companion object {
        var listener : MenuListener ?= null
        var list: MutableList<OrderModel> = ArrayList()


    }


    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //   var cardView: CardView = itemView.findViewById(R.id.container)
        var foodName: TextView ?= null
        var foodDescribed:TextView
        var price: TextView
        var itemNo: TextView
        var orderFood: Button
        var add:ImageButton
        var image:ImageView
        var minus:ImageButton

        //   val cardType : TextView


        init {
            this.foodName = itemView.findViewById(R.id.textFood)
            this.foodDescribed = itemView.findViewById(R.id.textFoodDescript)
            this.price = itemView.findViewById(R.id.textPrice)
            this.add = itemView.findViewById(R.id.buttonAdd)
            this.orderFood = itemView.findViewById(R.id.buttonInOrder)
            this.itemNo = itemView.findViewById(R.id.textNo)
            this.image = itemView.findViewById(R.id.imageFood)
            this.minus = itemView.findViewById(R.id.buttonRemove)
            // cardType = itemView.findViewById(R.id.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_new_order, parent, false))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val qty = holder.itemNo.text.toString()

        //  holder.quantity = modelList!![position].food_description
        holder.price.text = modelList!![position].price.toString()
        holder.foodName!!.text = modelList[position].name
        if (!modelList[position].imgUrl.isNullOrEmpty())
            Picasso.with(context).load(modelList[position].imgUrl).into(holder.image)

        holder.orderFood.setOnClickListener {
            Log.i("TAGGG","Text")
            val  model = OrderModel()

            if (qty=="0"){
                listener!!.menuListener(null, "Please select a valid Quantity")

            }else {
                model.food_description = holder.foodDescribed.text.toString()
                model.amount = ((holder.price.text.toString()).toInt() * (holder.itemNo.text.toString()).toInt()).toString()
                model.foodName = holder.foodName!!.text.toString()
                //  model!!.image = modelList[position].image
                model.quantity = holder.itemNo.text.toString()
                list.clear()
                list.add(model)
                listener!!.menuListener(list, "Add to DB")
            }
        }
        holder.minus.setOnClickListener {
            val minteger =(holder.itemNo.text.toString()).toInt()
            val mnteger = minteger - 1
            holder.itemNo.text = mnteger.toString()

        }
        holder.add.setOnClickListener {
            val minteger =(holder.itemNo.text.toString()).toInt()

            val mnteger = minteger + 1
            holder.itemNo.text = mnteger.toString()
        }
        // holder.itemView.buttonInOrder.setOnClickListener { Log.d("Holder", "Click") }
    }

    override fun getItemCount(): Int {
        return modelList!!.size
    }


}


package com.example.twiscode.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.R
import com.example.twiscode.models.Items
import com.squareup.picasso.Picasso

class ProductCartAdapter(
    val data: List<Items>,
    val context: Context,
    val total: TextView)
    : RecyclerView.Adapter<ProductCartAdapter.ViewHolder>() {

    private var total_price: Float = 0f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product_cart, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.data.get(position)

        Picasso.get().load(item.image).into(holder.imgProduct)
        holder.titleProduct?.text = item.title
        holder.categoryProduct?.text = item.category
        holder.priceProduct?.text = "Rp. ${item.prices}"

        holder.btnPlus?.setOnClickListener {
            val i: Int = holder.numberProduct?.text.toString().toInt() + 1
            holder.numberProduct!!.text = i.toString()

            this.total_price = i * item.prices
            this.total.text = "$total_price"
        }

        holder.btnMinus?.setOnClickListener {
            val i: Int = holder.numberProduct?.text.toString().toInt() - 1
            if (i >= 0) {
                holder.numberProduct!!.text = i.toString()

                this.total_price = i * item.prices
            }

            this.total.text = "$total_price"
        }

        this.total.text = "${item.prices + this.total.text.toString().toInt()}"
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun getTotalPrice() : Float {
        return total_price
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleProduct: TextView? = null
        var priceProduct: TextView? = null
        var categoryProduct: TextView? = null
        var numberProduct: TextView? = null

        var btnPlus: Button? = null
        var btnMinus: Button? = null
        var imgProduct: ImageView? = null

        init {
            titleProduct = itemView.findViewById(R.id.title_product)
            priceProduct = itemView.findViewById(R.id.price_product)
            categoryProduct = itemView.findViewById(R.id.category_product)
            numberProduct = itemView.findViewById(R.id.nums_items)

            btnPlus = itemView.findViewById(R.id.btn_plus_cart)
            btnMinus = itemView.findViewById(R.id.btn_minus_cart)
            imgProduct = itemView.findViewById(R.id.img_product)
        }
    }

}

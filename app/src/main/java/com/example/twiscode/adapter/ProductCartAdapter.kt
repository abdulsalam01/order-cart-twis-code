package com.example.twiscode.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carteasy.v1.lib.Carteasy
import com.example.twiscode.R
import com.example.twiscode.models.Items
import com.squareup.picasso.Picasso

class ProductCartAdapter(
    val data: List<Items>,
    val context: Context)
    : RecyclerView.Adapter<ProductCartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product_cart, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.data.get(position)

        Picasso.get().load(item.image).into(holder.imgProduct)
//        holder.titleProduct?.text = item.title
//        holder.categoryProduct?.text = item.category
//        holder.priceProduct?.text = "Rp. ${item.price}"

        holder.btnPlus?.setOnClickListener {
            val i: Int = holder.numberProduct?.text as Int + 1
            holder.numberProduct!!.text = i.toString()
        }

        holder.btnMinus?.setOnClickListener {
            val i: Int = holder.numberProduct?.text as Int - 1
            if (i >= 0) holder.numberProduct!!.text = i.toString()
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
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
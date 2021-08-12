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
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.squareup.picasso.Picasso

class ProductAdapter(val data: List<Items>, val context: Context)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Items = this.data.get(position)

        Picasso.get().load(item.image).into(holder.imgProduct)
        holder.titleProduct?.text = item.title
        holder.categoryProduct?.text = item.category
        holder.priceProduct?.text = "Rp. ${item.price}"

        holder.btnCart?.setOnClickListener {
            val cart = Carteasy()

            if (!cart.doesIDExistInCart(item.id.toString(), this.context)) {
                cart.add(item.id.toString(), "product", item.image)
                cart.commit(this.context)
            } else {
                Snackbar.make(holder.btnCart!!, "Already added", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleProduct: TextView? = null
        var priceProduct: TextView? = null
        var categoryProduct: TextView? = null

        var btnCart: Button? = null
        var imgProduct: ImageView? = null

        init {
            titleProduct = itemView.findViewById(R.id.title_product)
            priceProduct = itemView.findViewById(R.id.price_product)
            categoryProduct = itemView.findViewById(R.id.category_product)

            btnCart = itemView.findViewById(R.id.btn_add_cart)
            imgProduct = itemView.findViewById(R.id.img_product)
        }
    }

}
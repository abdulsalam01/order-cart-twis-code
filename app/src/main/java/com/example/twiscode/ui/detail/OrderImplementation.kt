package com.example.twiscode.ui.detail

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.RecyclerView
import com.carteasy.v1.lib.Carteasy
import com.example.twiscode.R
import com.example.twiscode.adapter.ProductCartAdapter
import com.nu.nucount.extension.database.ItemDb
import org.w3c.dom.Text

class OrderImplementation: OrderInterface {

    override fun onCreateMenu(context: Context, menu: Menu?): Menu {
        ((context) as AppCompatActivity).menuInflater.inflate(R.menu.main_menu, menu)
        this.updateCartItems(context, menu, 0)

        return menu!!
    }

    override fun onMenuSelected(context: Context, item: MenuItem): Boolean {
        return true
    }

    override fun onSetupToolbar(context: Context) {
        val toolbar = ((context) as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)
        context.setSupportActionBar(toolbar)
    }

    override fun updateCartItems(context: Context, menu: Menu?, num: Int) {
        val item: MenuItem = menu!!.findItem(R.id.action_cart)
        // deprecated
        MenuItemCompat.setActionView(item, R.layout.badge_cart)

        val r_layout: RelativeLayout = MenuItemCompat.getActionView(item) as RelativeLayout
        val tv_num_items: TextView = r_layout.findViewById(R.id.tv_number_items)

        tv_num_items.text = "$num"
    }

    override fun getProducts(progressBar: ProgressBar,
                             list: RecyclerView, context: Context) {
        val itemDb = ItemDb(context)
        val text_total = ((context) as AppCompatActivity).findViewById<TextView>(R.id.total_price)

        list.adapter = ProductCartAdapter(itemDb.getAll(), context = context, text_total)
        (list.adapter as ProductCartAdapter).notifyDataSetChanged()
    }

    override fun onOrderClick(context: Context) {
        val db = ItemDb(context)

        if (db.deleteAll() > 0) {
            val text_total = ((context) as AppCompatActivity).findViewById<TextView>(R.id.total_price)

            text_total.text = context.getString(R.string.zero)
            Toast.makeText(context, "Order Successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
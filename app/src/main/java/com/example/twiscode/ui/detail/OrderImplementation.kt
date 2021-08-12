package com.example.twiscode.ui.detail

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.RecyclerView
import com.carteasy.v1.lib.Carteasy
import com.example.twiscode.R
import com.example.twiscode.adapter.ProductCartAdapter
import com.example.twiscode.models.Items

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
        val carteasy = Carteasy()
        val items = carteasy.ViewAll(context).entries
        val itemsList = ArrayList<Items>(items.size + 1)

        items.iterator().forEach { it ->
            it.value.iterator().forEach { t ->
                itemsList.add(t.value as Items)
            }
        }

        list.adapter = ProductCartAdapter(itemsList, context = context)
    }
}
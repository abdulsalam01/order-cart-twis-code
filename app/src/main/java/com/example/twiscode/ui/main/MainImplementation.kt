package com.example.twiscode.ui.main

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.R
import com.example.twiscode.adapter.ProductAdapter
import com.example.twiscode.models.Items
import com.example.twiscode.services.ApiService
import com.example.twiscode.services.RestClient
import com.example.twiscode.ui.detail.OrderActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainImplementation: MainInterface {

    override fun onMenuSelected(context: Context, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                // change page
            }
        }

        return true
    }

    override fun onSetupToolbar(context: Context) {
        val toolbar = ((context) as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)
        context.setSupportActionBar(toolbar)
    }

    override fun onCreateMenu(context: Context, menu: Menu?): Menu {
        ((context) as AppCompatActivity).menuInflater.inflate(R.menu.main_menu, menu)
        this.updateCartItems(context, menu, 0)

        return menu!!
    }

    override fun updateCartItems(context: Context, menu: Menu?, num: Int): Unit {
        val item: MenuItem = menu!!.findItem(R.id.action_cart)
        // deprecated
        MenuItemCompat.setActionView(item, R.layout.badge_cart)

        val r_layout: RelativeLayout = MenuItemCompat.getActionView(item) as RelativeLayout
        val tv_num_items: TextView = r_layout.findViewById(R.id.tv_number_items)

        tv_num_items.text = "$num"
        this.onClick(tv_num_items, context)
    }

    private fun onClick(textView: TextView, context: Context) {
        textView.setOnClickListener {
            val intent = Intent(context, OrderActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            ((context) as AppCompatActivity).startActivity(intent)
        }
    }

    override fun initRestClient(context: Context): ApiService {
        val apiService: ApiService = RestClient.getRetrofitClient(context)
            .create(ApiService::class.java)

        return apiService
    }

    override fun getProducts(api: ApiService, progressBar: ProgressBar,
                             list: RecyclerView, context: Context, sort: String) {
        progressBar.visibility = View.VISIBLE

        api.getProductList(sort).enqueue(object : Callback<List<Items>> {

            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                val items = response.body()

                if (items!!.size > 0) {
                    list.adapter = ProductAdapter(items, context = context)
                }

                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Snackbar.make(list, t.message!!, Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    override fun getProductByCategory(
        api: ApiService,
        progressBar: ProgressBar,
        list: RecyclerView,
        context: Context,
        sort: String
    ) {
        api.getProductListByCategory(sort).enqueue(object : Callback<List<Items>> {

            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                val items = response.body()

                if (items!!.size > 0) {
                    list.adapter = ProductAdapter(items, context = context)
                }

                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Snackbar.make(list, t.message!!, Snackbar.LENGTH_SHORT).show()
            }

        })
    }
}
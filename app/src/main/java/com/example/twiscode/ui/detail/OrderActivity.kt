package com.example.twiscode.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.R
import com.example.twiscode.models.Items
import com.example.twiscode.services.ApiService
import com.example.twiscode.ui.BaseApp
import com.example.twiscode.ui.main.MainImplementation

class OrderActivity : BaseApp() {

    private lateinit var orderImplementation: OrderImplementation
    private lateinit var menu: Menu

    private lateinit var list: RecyclerView
    private lateinit var order: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        build(this, R.id.parentView)
        hide()

        this.list = findViewById(R.id.rv_products)
        this.order = findViewById(R.id.order)

        this.orderImplementation = OrderImplementation()
        this.orderImplementation.onSetupToolbar(this)

        this.list.layoutManager = LinearLayoutManager(this)
        this.orderImplementation.getProducts(this.get(), this.list, this)

        this.order.setOnClickListener {
            this.orderImplementation.onOrderClick(this)
            this.orderImplementation.getProducts(this.get(), this.list, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.orderImplementation.onCreateMenu(this, menu)
        super.onCreateOptionsMenu(menu)

        this.menu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.orderImplementation.onMenuSelected(this, item)
        return super.onOptionsItemSelected(item)
    }
}
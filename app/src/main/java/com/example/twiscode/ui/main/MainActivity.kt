package com.example.twiscode.ui.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.R
import com.example.twiscode.models.Items
import com.example.twiscode.services.ApiService
import com.example.twiscode.ui.BaseApp
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : BaseApp() {

    private lateinit var mainImplementation: MainImplementation
    private lateinit var menu: Menu

    private lateinit var list: RecyclerView
    private lateinit var api: ApiService

    private lateinit var filter: ExtendedFloatingActionButton
    private lateinit var category: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        build(this, R.id.parentView)

        this.list = findViewById(R.id.rv_products)
        this.filter = findViewById(R.id.filter)
        this.category = findViewById(R.id.category)

        this.mainImplementation = MainImplementation()
        this.mainImplementation.onSetupToolbar(this)

        this.list.layoutManager = GridLayoutManager(this, 2)

        this.api = this.mainImplementation.initRestClient(this)
        this.mainImplementation.getProducts(this.api, this.get(), this.list, this, "asc")

        this.filter.setOnClickListener {
            if (it.isLongClickable)
                this.mainImplementation.getProducts(this.api, this.get(), this.list, this, "asc")
            else
                this.mainImplementation.getProducts(this.api, this.get(), this.list, this, "desc")
        }

        this.category.setOnClickListener {
            if (it.isLongClickable)
                this.mainImplementation.getProducts(this.api, this.get(), this.list, this, "asc")
            else
                this.mainImplementation.getProductByCategory(this.api, this.get(), this.list, this, "asc")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.mainImplementation.onCreateMenu(this, menu)
        super.onCreateOptionsMenu(menu)

        this.menu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.mainImplementation.onMenuSelected(this, item)
        return super.onOptionsItemSelected(item)
    }
}
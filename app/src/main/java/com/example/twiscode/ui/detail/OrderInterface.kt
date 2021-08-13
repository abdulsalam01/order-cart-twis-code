package com.example.twiscode.ui.detail

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

interface OrderInterface {
    fun onCreateMenu(context: Context, menu: Menu?): Menu
    fun onMenuSelected(context: Context, item: MenuItem): Boolean
    fun onSetupToolbar(context: Context): Unit
    fun updateCartItems(context: Context, menu: Menu?, num: Int): Unit
    fun getProducts(progressBar: ProgressBar, list: RecyclerView, context: Context): Unit
    fun onOrderClick(context: Context): Unit
}
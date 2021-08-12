package com.example.twiscode.ui.main

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.services.ApiService

interface MainInterface {
    fun onCreateMenu(context: Context, menu: Menu?): Menu
    fun onMenuSelected(context: Context, item: MenuItem): Boolean
    fun onSetupToolbar(context: Context): Unit
    fun updateCartItems(context: Context, menu: Menu?, num: Int): Unit
    fun initRestClient(context: Context): ApiService
    fun getProducts(api: ApiService, progressBar: ProgressBar, list: RecyclerView, context: Context): Unit
}
package com.example.twiscode.services

import com.example.twiscode.Const
import com.example.twiscode.models.Items
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(Const.ITEM_URL_ALT)
    fun getProductList(): Call<List<Items>>
}
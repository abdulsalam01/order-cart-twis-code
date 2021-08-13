package com.example.twiscode.services

import com.example.twiscode.Const
import com.example.twiscode.models.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Const.ITEM_URL_ALT)
    fun getProductList(@Query("sort") sort: String?): Call<List<Items>>

    @GET(Const.ITEM_BY_CATEGORY_ALT)
    fun getProductListByCategory(@Query("sort") sort: String?): Call<List<Items>>
}
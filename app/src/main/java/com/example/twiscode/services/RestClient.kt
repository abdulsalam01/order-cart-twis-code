package com.example.twiscode.services

import android.content.Context
import com.example.twiscode.Const
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(context: Context): Retrofit {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val gson: Gson = GsonBuilder().setLenient().create()

        if (this.retrofit === null) {
            this.retrofit = Retrofit.Builder()
                    .baseUrl(Const.BASE_URL_ALT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }

        return this.retrofit!!
    }
}
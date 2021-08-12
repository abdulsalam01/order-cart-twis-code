package com.example.twiscode.models

import com.google.gson.annotations.SerializedName

data class Items(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("price")
        var price: Float,
        @SerializedName("description")
        var description: String,
        @SerializedName("category")
        var category: String,
        @SerializedName("image")
        var image: String
) {
}
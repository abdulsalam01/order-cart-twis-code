package com.example.twiscode.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import androidx.preference.PreferenceManager
import com.example.twiscode.models.Items

object SharedPref {

    private val ID = "product_session_id"

    fun getPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    fun addProductCart(context: Context, item: Items) {
        val editor = getPreferences(context).edit()

        editor.putInt(ID, item.id)
        editor.apply()
    }

    fun getProductCart(context: Context) : Items {
        val sharePreferenceManager = getPreferences(context)
        return Items(sharePreferenceManager.getInt(ID, -1),
            "", 0f, "", "", ""
        )
    }

    fun clearProductCart(context: Context) {
        val editor = getPreferences(context).edit()

        editor.clear()
        editor.apply()
    }
}
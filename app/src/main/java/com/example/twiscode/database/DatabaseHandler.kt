package com.nu.nucount.extension.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class DatabaseHandler(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "item_cart"
    }

    // general-case
    protected val ID = "id"

    protected val TABLE_ITEM = "items"

    protected val TITLE = "title_product"
    protected val PRICE = "price_product"
    protected val DESCRIPTION = "desc_product"
    protected val CATEGORY = "category_product"
    protected val IMAGE = "image_product"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ITEM_TABLE = ("CREATE TABLE $TABLE_ITEM (" +
                "$ID INTEGER PRIMARY KEY," +
                "$TITLE TEXT," +
                "$PRICE TEXT," +
                "$DESCRIPTION TEXT," +
                "$CATEGORY TEXT," +
                "$IMAGE TEXT)")

        db?.execSQL(CREATE_ITEM_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query_item = "DROP TABLE IF EXISTS $TABLE_ITEM"

        db!!.execSQL(query_item)
        onCreate(db)
    }
}
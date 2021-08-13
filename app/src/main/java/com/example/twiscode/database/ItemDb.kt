package com.nu.nucount.extension.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.example.twiscode.models.Items
import com.nu.nucount.core.helper.SqlOperation

class ItemDb(context: Context) : SqlOperation<Items>, DatabaseHandler(context) {

    override fun create(data: Items): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(ID, data.id)
        contentValues.put(TITLE, data.title)
        contentValues.put(PRICE, data.prices)
        contentValues.put(DESCRIPTION, data.description)
        contentValues.put(CATEGORY, data.category)
        contentValues.put(IMAGE, data.image)

        val success = db.insert(TABLE_ITEM, null, contentValues)
        return success
    }

    override fun update(data: Items, id: Any): Long {
        TODO("Not yet implemented")
    }

    override fun delete(id: Any): Int {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_ITEM,null,null)

        db.close()
        return success
    }

    override fun getAll(): List<Items> {
        val data: ArrayList<Items> = ArrayList()
        val selectQuery = "SELECT a.* " +
                "FROM $TABLE_ITEM a"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val item = Items(cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TITLE)),
                    cursor.getFloat(cursor.getColumnIndex(PRICE)),
                    cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(CATEGORY)),
                    cursor.getString(cursor.getColumnIndex(IMAGE))
                )

                data.add(item)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return data
    }

    override fun getById(id: Any): Items {
        var data: Items? = null
        val selectQuery = "SELECT a.* " +
                "FROM $TABLE_ITEM a " +
                "WHERE $ID = $id"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return Items(0, "", 0f, "", "", "")
        }

        if (cursor.moveToFirst()) {
            data = Items(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(TITLE)),
                cursor.getFloat(cursor.getColumnIndex(PRICE)),
                cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(CATEGORY)),
                cursor.getString(cursor.getColumnIndex(IMAGE))
            )

        } else {
            data = Items(0, "", 0f, "", "", "")
            return data
        }

        cursor.close()
        return data
    }

}
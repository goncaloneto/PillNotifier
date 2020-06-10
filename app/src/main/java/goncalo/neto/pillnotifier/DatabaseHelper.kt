package goncalo.neto.pillnotifier

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*


class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        var createTable = "CREATE TABLE $PILLS_TABLE_NAME ($PILLS_ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, $PILLS_NAME_COL TEXT, $PILLS_QUANTITY_COL INTEGER, $PILLS_REMAINING_COL INTEGER, $PILLS_EXP_DATE_COL TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $PILLS_TABLE_NAME")
        onCreate(db)
    }

    fun AddPillBox(name: String, qty: Int, remaining: Int, expDate: Long): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PILLS_NAME_COL, name)
        contentValues.put(PILLS_QUANTITY_COL, qty)
        contentValues.put(PILLS_REMAINING_COL, remaining)
        contentValues.put(PILLS_EXP_DATE_COL, expDate)
        val result =
            db.insert(PILLS_TABLE_NAME, null, contentValues)

        //if date as inserted incorrectly it will return -1
        return result != -1L
    }

    val ListBoxes: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $PILLS_TABLE_NAME", null)
        }

    companion object {
        const val DATABASE_NAME = "pills.db"

        const val PILLS_TABLE_NAME = "pill_boxes_data"

        const val PILLS_ID_COL = "ID"
        const val PILLS_NAME_COL = "NAME"
        const val PILLS_QUANTITY_COL = "QTY"
        const val PILLS_REMAINING_COL = "REMAINING"
        const val PILLS_EXP_DATE_COL = "EXPDATE"
    }
}
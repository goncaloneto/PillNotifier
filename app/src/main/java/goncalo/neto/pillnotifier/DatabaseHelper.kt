package goncalo.neto.pillnotifier

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        var createTable = "CREATE TABLE $PILLS_TABLE_NAME ($ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_COL TEXT, $QTY_COL INTEGER, $REMAINING_COL REAL, $EXP_DATE_COL TEXT, $START_DATE_COL TEXT, $DOSAGE_COL REAL)"
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

    fun AddPillBox(name: String, qty: Int, remaining: Float, dosage: Float, expDate: Long, startDate: Long): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_COL, name)
        contentValues.put(QTY_COL, qty)
        contentValues.put(REMAINING_COL, remaining)
        contentValues.put(EXP_DATE_COL, expDate)
        contentValues.put(START_DATE_COL, startDate)
        contentValues.put(DOSAGE_COL, dosage)
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

        const val ID_COL = "ID"
        const val ID_COL_INDEX = 0
        const val NAME_COL = "NAME"
        const val NAME_COL_INDEX = 1
        const val QTY_COL = "QTY"
        const val QTY_COL_INDEX = 2
        const val REMAINING_COL = "REMAINING"
        const val REMAINING_COL_INDEX = 3
        const val EXP_DATE_COL = "EXPDATE"
        const val EXP_DATE_COL_INDEX = 4
        const val START_DATE_COL = "STARTDATE"
        const val START_DATE_COL_INDEX = 5
        const val DOSAGE_COL = "DOSAGE"
        const val DOSAGE_COL_INDEX = 6
    }
}
package tech.stacka.bizmaster.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelper(val context: Context) : SQLiteOpenHelper(
    context,
    "bizmaster", null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTablequery =
            "CREATE TABLE IF NOT EXISTS transactions(date INTEGER primary key,amount float not null,amountType boolean,note varchar(50),category varchar(50))"
        db?.execSQL(createTablequery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS transactions ")
        onCreate(db)
    }

    fun addExpense(amount: Float, notes: String, category: String) {
        val date = System.currentTimeMillis() / 1000
        val amountType = false
//        val insertExpenseQuery =
//            "INSERT INTO transactions(date,amount,amountType,note,category) values ($date,$amount,$amountType,'$notes','$category')"
//        val db = this.writableDatabase
//        db.execSQL(insertExpenseQuery)

        val rowContents = ContentValues()
        rowContents.put("date", date)
        rowContents.put("amount", amount)
        rowContents.put("amountType", amountType)
        rowContents.put("note", notes)
        rowContents.put("category", category)
        val db = this.writableDatabase
        val response = db.insert("transactions", null, rowContents)
        db.close()
        Log.d("add all", response.toString())

    }
}
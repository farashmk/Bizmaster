package tech.stacka.bizmaster.helper

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelper(private val context: Context) : SQLiteOpenHelper(
    context,
    "bizmaster", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTablequery =
            "CREATE TABLE IF NOT EXISTS transactions(date INTEGER primary key,amount float not null,amountType boolean,note varchar(50),category varchar(50))"
        db?.execSQL(createTablequery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS transactions ")
        onCreate(db)
    }

    fun addExpense(amount: Float, notes: String, category: String): Long {
        val date =
            System.currentTimeMillis() / 1000     //getting current time to milliseconds(timestamp)
        val amountType = false                           //false represent Expense
        val rowContents =
            ContentValues()                //create a content value object for inserting values to table
        rowContents.put("date", date)                    //insert date into table
        rowContents.put("amount", amount)                //insert amount into table
        rowContents.put("amountType", amountType)        //insert amountType into table
        rowContents.put("note", notes)                   //insert notes into table
        rowContents.put("category", category)            //insert category into table
        val db = this.writableDatabase                   //getting current database
        val response = db.insert("transactions", null, rowContents)   //
        db.close()                                                                          //to prevent memory close database
        Log.d("add expense", response.toString())
        return response

    }

    fun getExpense(): Cursor {
        val expnceQuery = "SELECT amount,category FROM transactions WHERE amountType = 0"
        val db = this.readableDatabase
        return db.rawQuery(expnceQuery, null)
    }

    fun addIncome(amount: Float, notes: String, category: String): Long {
        val date = System.currentTimeMillis() / 1000
        val amountType = true
        val rowContents = ContentValues()
        rowContents.put("date", date)
        rowContents.put("amount", amount)
        rowContents.put("amountType", amountType)
        rowContents.put("note", notes)
        rowContents.put("category", category)
        val db = this.writableDatabase
        val response = db.insert("transactions", null, rowContents)
        db.close()
        Log.d("add income", response.toString())
        return response
    }

    fun getAllTransactions(): Cursor {
        val query = "SELECT amount,date,amountType FROM transactions"
        val db = this.readableDatabase
        return db.rawQuery(query, null)
    }

}
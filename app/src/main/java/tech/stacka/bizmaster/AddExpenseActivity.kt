package tech.stacka.bizmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_expense.*
import tech.stacka.bizmaster.helper.SqliteHelper
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    private var selectedCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val date = Calendar.getInstance().time              //get current date from device calender
        val sdf = SimpleDateFormat("dd/MM/yyyy")   //arrange the date in dd mm yy format
        val dateString = sdf.format(date)                  //format to the date
        expense_date.text = dateString                     //date value to the date textview

        add_expense_btn.setOnClickListener { view ->

            val amount = etExpenseAmount.text.toString()
            val notes = etExpenseNotes.text.toString()
            when {
                TextUtils.isEmpty(amount) -> Snackbar.make(
                    view,
                    "Please Enter the Amount",
                    Snackbar.LENGTH_LONG
                ).show()
                TextUtils.isEmpty(notes) -> Snackbar.make(
                    view,
                    "Please Enter the Notes",
                    Snackbar.LENGTH_LONG
                ).show()
                TextUtils.isEmpty(selectedCategory) -> Snackbar.make(
                    view,
                    "Please Select a Category",
                    Snackbar.LENGTH_LONG
                ).show()
                else -> {
                    val sqliteHelper = SqliteHelper(this)
                    sqliteHelper.addExpense(amount.toFloat(), notes, selectedCategory!!)
                }
            }
        }

        cardFood.setOnClickListener {
            selectedCategory = "food"

        }

        cardCar.setOnClickListener {
            selectedCategory = "car"
        }

        cardGift.setOnClickListener {
            selectedCategory = "gift"
        }

        cardCommunication.setOnClickListener {
            selectedCategory = "communication"
        }

        cardEntertaintment.setOnClickListener {
            selectedCategory = "entertaintment"
        }

        cardEatout.setOnClickListener {
            selectedCategory = "Eatout"
        }

        cardHome.setOnClickListener {
            selectedCategory = "home"
        }
    }
}

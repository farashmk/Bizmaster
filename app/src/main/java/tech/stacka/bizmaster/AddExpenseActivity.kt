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
                    if (sqliteHelper.addExpense(amount.toFloat(), notes, selectedCategory!!) > 0) {
                        Snackbar.make(view, "Expence added", Snackbar.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Snackbar.make(view, "Expence adding failed", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        cardFood.setOnClickListener {
            selectedCategory = "food"
            cardFood.background=getDrawable(R.drawable.card_selection)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)



        }

        cardCar.setOnClickListener {
            selectedCategory = "car"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_selection)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)

        }

        cardGift.setOnClickListener {
            selectedCategory = "gift"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_selection)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)

        }

        cardCommunication.setOnClickListener {
            selectedCategory = "communication"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_selection)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)
        }
        cardHealth.setOnClickListener {
            selectedCategory="Health"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_selection)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)

        }

        cardEntertaintment.setOnClickListener {
            selectedCategory = "entertaintment"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_selection)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)

        }

        cardEatout.setOnClickListener {
            selectedCategory = "Eatout"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_selection)
            cardHome.background=getDrawable(R.drawable.card_unselected)
            cardHealth.background=getDrawable(R.drawable.card_unselected)

        }

        cardHome.setOnClickListener {
            selectedCategory = "home"
            cardFood.background=getDrawable(R.drawable.card_unselected)
            cardCar.background=getDrawable(R.drawable.card_unselected)
            cardGift.background=getDrawable(R.drawable.card_unselected)
            cardCommunication.background=getDrawable(R.drawable.card_unselected)
            cardEntertaintment.background=getDrawable(R.drawable.card_unselected)
            cardEatout.background=getDrawable(R.drawable.card_unselected)
            cardHome.background=getDrawable(R.drawable.card_selection)
            cardHealth.background=getDrawable(R.drawable.card_unselected)

        }


    }
}

package tech.stacka.bizmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_income.*
import tech.stacka.bizmaster.helper.SqliteHelper
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeActivity : AppCompatActivity() {
    private var selectedCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        val date= Calendar.getInstance().time
        val sdf=SimpleDateFormat("dd/MM/YYYY")
        val dateString = sdf.format(date)
        income_date.text = dateString

        btn_add_income.setOnClickListener {view ->

            val amount = et_income_amount.text.toString()
            val notes = et_income_notes.text.toString()
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
                    if (sqliteHelper.addIncome(amount.toFloat(), notes, selectedCategory!!) > 0) {
                        Snackbar.make(view, "Income added", Snackbar.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Snackbar.make(view, "Income adding failed", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }


        }
        card_salary.setOnClickListener {
            selectedCategory="salary"
            card_salary.background=getDrawable(R.drawable.card_selection)
            card_rent.background=getDrawable(R.drawable.card_unselected)
            card_bonus.background=getDrawable(R.drawable.card_unselected)
            card_commission.background=getDrawable(R.drawable.card_unselected)
            card_profit.background=getDrawable(R.drawable.card_unselected)
        }
        card_profit.setOnClickListener {
            selectedCategory="Profit"
            card_salary.background=getDrawable(R.drawable.card_unselected)
            card_rent.background=getDrawable(R.drawable.card_unselected)
            card_bonus.background=getDrawable(R.drawable.card_unselected)
            card_commission.background=getDrawable(R.drawable.card_unselected)
            card_profit.background=getDrawable(R.drawable.card_selection)
        }
        card_bonus.setOnClickListener {
            selectedCategory="Bonus"
            card_salary.background=getDrawable(R.drawable.card_unselected)
            card_rent.background=getDrawable(R.drawable.card_unselected)
            card_bonus.background=getDrawable(R.drawable.card_selection)
            card_commission.background=getDrawable(R.drawable.card_unselected)
            card_profit.background=getDrawable(R.drawable.card_unselected)
        }
        card_commission.setOnClickListener {
            selectedCategory="commision"
            card_salary.background=getDrawable(R.drawable.card_unselected)
            card_rent.background=getDrawable(R.drawable.card_unselected)
            card_bonus.background=getDrawable(R.drawable.card_unselected)
            card_commission.background=getDrawable(R.drawable.card_selection)
            card_profit.background=getDrawable(R.drawable.card_unselected)
        }
        card_rent.setOnClickListener {
            selectedCategory="Rent"
            card_salary.background=getDrawable(R.drawable.card_unselected)
            card_rent.background=getDrawable(R.drawable.card_selection)
            card_bonus.background=getDrawable(R.drawable.card_unselected)
            card_commission.background=getDrawable(R.drawable.card_unselected)
            card_profit.background=getDrawable(R.drawable.card_unselected)
        }
    }
}

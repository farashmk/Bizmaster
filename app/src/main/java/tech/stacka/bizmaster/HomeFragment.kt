package tech.stacka.bizmaster


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import kotlinx.android.synthetic.main.add_income_expence_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import tech.stacka.bizmaster.helper.SqliteHelper
import tech.stacka.bizmaster.models.CustomAdapter
import tech.stacka.bizmaster.models.Transactions

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var prefs: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = activity!!.getSharedPreferences(
            "com.stackatech.bizmaster",
            AppCompatActivity.MODE_PRIVATE
        )
        val name = prefs.getString("name", "no name")

        tv_home_name.text = "Hi, $name"

        btn_add.setOnClickListener {
            add_layout.visibility = View.VISIBLE
        }
        addLayoutParent.setOnClickListener {
            add_layout.visibility = View.GONE
        }
        btn_add_expense.setOnClickListener {
            val clickIntent = Intent(activity, AddExpenseActivity::class.java)
            startActivity(clickIntent)
        }
        btn_add_income.setOnClickListener {

            val clickintent = Intent(activity, AddIncomeActivity::class.java)
            startActivity(clickintent)
        }
        recyclerview.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    }

    override fun onResume() {
        super.onResume()
        add_layout.visibility = View.GONE
        val sqliteHelper = SqliteHelper(activity!!.application)
        val data = sqliteHelper.getAllTransactions()

        val transactionList = ArrayList<Transactions>()
        var expense = 0
        var income = 0
        var balance=0
        if (data.moveToFirst()) {

            for (i in 0 until data.count) {
                val amount = data.getInt(0)
                val amountType = data.getInt(2) > 0
                val date = data.getLong(1)
                val note = data.getString(3)
                if (amountType) {
                    income += amount
                } else
                    expense += amount
                balance=income-expense

                val transactions = Transactions(amount, amountType, date, note)
                transactionList.add(transactions)
                data.moveToNext()
                var adapter = CustomAdapter(transactionList)
                recyclerview.adapter = adapter
            }
            tvexpense.text = expense.toString()
            tvincome.text = income.toString()
            tvBalance.text=balance.toString()
        }

    }


}

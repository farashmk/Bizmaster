package tech.stacka.bizmaster


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_income_expence_layout.*
import kotlinx.android.synthetic.main.fragment_home.*

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
    }


}

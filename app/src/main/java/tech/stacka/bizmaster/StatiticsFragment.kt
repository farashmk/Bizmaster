package tech.stacka.bizmaster


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_statitics.*
import tech.stacka.bizmaster.helper.SqliteHelper
import tech.stacka.bizmaster.models.Transactions
import tech.stacka.bizmaster.utils.MyXAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class StatiticsFragment : Fragment() {

    private var foodCount: Float = 0F
    private var carCount: Float = 0F
    private var giftCount: Float = 0F
    private var communicationCount: Float = 0F
    private var healthCount: Float = 0F
    private var entertaintmentCount: Float = 0F
    private var eatingoutCount: Float = 0F
    private var homeCount: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statitics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // call the sqlite helper then store all expense datas in rows variable
        val sqliteHelper = SqliteHelper(activity!!.application)
        val rows = sqliteHelper.getExpense()

        if (rows.moveToFirst()) {

            for (i in 0 until rows.count) {
                val category = rows.getString(1)
                val amount = rows.getInt(0)

                Log.d("cursor value", rows.getString(1))
                when (category) {
                    "car" -> carCount += amount
                    "food" -> foodCount += amount
                    "gift" -> giftCount += amount
                    "communication" -> communicationCount += amount
                    "health" -> healthCount += amount
                    "entertaintment" -> entertaintmentCount += amount
                    "eatingout" -> eatingoutCount += amount
                    "home" -> homeCount += amount
                }

                rows.moveToNext()

            }

            pieChart.holeRadius = 0f               //set center circle radius zero
            pieChart.transparentCircleRadius = 0f  //set transparent center circle radius zero

            pieChart.setDrawEntryLabels(false)     //pie chart label removed


            val entries = ArrayList<PieEntry>()
            entries.add(PieEntry(carCount, "car"))
            entries.add(PieEntry(foodCount, "food"))
            entries.add(PieEntry(giftCount, "gift"))
            entries.add(PieEntry(communicationCount, "communication"))
            entries.add(PieEntry(healthCount, "health"))
            entries.add(PieEntry(entertaintmentCount, "entertaintment"))
            entries.add(PieEntry(eatingoutCount, "eating"))
            entries.add(PieEntry(homeCount, "home"))

            val pieDataSet = PieDataSet(entries, null)

            val colors = intArrayOf(
                Color.rgb(11, 39, 254),
                Color.rgb(110, 116, 79),
                Color.rgb(238, 9, 9),
                Color.rgb(106, 150, 31),
                Color.rgb(179, 100, 53),
                Color.rgb(225, 21, 205),
                Color.rgb(238, 9, 9)
            )

            pieDataSet.colors = colors.toList()

            val piedata = PieData(pieDataSet)
            pieChart.data = piedata


        } else {
            Log.d("cursor value", "Empty")

        }

        val data = sqliteHelper.getAllTransactions()

        val transactionList = ArrayList<Transactions>()
        val dateList = ArrayList<String>()
        val expenseEntryList = ArrayList<Entry>()
        val incomeEntryList = ArrayList<Entry>()

        if (data.moveToFirst()) {

            for (i in 0 until data.count) {
                val amount = data.getInt(0)
                val date = data.getLong(1)
                val amountType = data.getInt(2) > 0
                val note = data.getString(3)

                val transactions = Transactions(amount, amountType, date, note)
                transactionList.add(transactions)
                data.moveToNext()
            }
        }

        val incomeMap=HashMap<String,Long>()
        val expenseMap=HashMap<String,Long>()

            for (transaction in transactionList) {
                Log.d("Transaction", transaction.toString())
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val sdfdate = sdf.format(Date(transaction.date * 1000))
                if (incomeMap[sdfdate] == null) {
                    incomeMap[sdfdate] = 0
                }
                if (expenseMap[sdfdate] == null) {
                    expenseMap[sdfdate] = 0
                }
                if (transaction.amountType) {
                    incomeMap[sdfdate] = incomeMap[sdfdate]!! + transaction.amount
                } else {
                    expenseMap[sdfdate] = expenseMap[sdfdate]!! + transaction.amount
                }

            }
            val dateIncome = ArrayList<String>()
            val amountIncome = ArrayList<Long>()
            val amountExpense = ArrayList<Long>()
            for ((k, v) in incomeMap) {
                dateIncome.add(k)
                amountIncome.add(v)
            }
            for ((k, v) in expenseMap) {
                amountExpense.add(v)
            }
        Log.d("Income", "$dateIncome=>$incomeMap=>$expenseMap")

        for (i in 0 until (dateIncome.size)) {
            incomeEntryList.add(Entry(i.toFloat(), amountIncome[i].toFloat()))
            expenseEntryList.add(Entry(i.toFloat(), amountExpense[i].toFloat()))
        }

        if (expenseEntryList.isNotEmpty() || incomeEntryList.isNotEmpty()) {

            chart.description.isEnabled = false
            chart.animateY(1000, Easing.Linear)
            chart.viewPortHandler.setMaximumScaleX(1.5f)
            chart.xAxis.setDrawGridLines(false)
            chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            chart.xAxis.isGranularityEnabled = true
            chart.legend.isEnabled = false
            chart.fitScreen()
            chart.isAutoScaleMinMaxEnabled = true
            chart.scaleX = 1f
            chart.setPinchZoom(true)
            chart.isScaleXEnabled = true
            chart.isScaleYEnabled = false
            chart.axisLeft.textColor = Color.BLACK
            chart.xAxis.textColor = Color.BLACK
            chart.axisLeft.setDrawAxisLine(false)
            chart.xAxis.setDrawAxisLine(false)
            chart.setDrawMarkers(false)
            chart.xAxis.labelCount = 5
            val rightAxix = chart.axisRight
            rightAxix.setDrawGridLines(false)
            rightAxix.setDrawZeroLine(false)
            rightAxix.setDrawAxisLine(false)
            rightAxix.setDrawLabels(false)

            val expenseSet = LineDataSet(expenseEntryList, "Expense")
            expenseSet.setDrawCircles(false)
            expenseSet.lineWidth = 2.5f
            expenseSet.setDrawFilled(true)
            expenseSet.setDrawValues(false)
            expenseSet.mode = LineDataSet.Mode.LINEAR
            expenseSet.color = Color.RED

            val incomeSet = LineDataSet(incomeEntryList, "Income")
            incomeSet.setDrawCircles(false)
            incomeSet.lineWidth = 2.5f
            incomeSet.setDrawFilled(true)
            incomeSet.setDrawValues(false)
            incomeSet.mode = LineDataSet.Mode.LINEAR
            incomeSet.color = Color.GREEN

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(expenseSet) // add the datasets
            dataSets.add(incomeSet)

            val lineData = LineData(dataSets)
            val xAxis = chart.xAxis
            xAxis.valueFormatter = MyXAxisValueFormatter(dateIncome)

            chart.data = lineData
            chart.invalidate()
        }


    }
}

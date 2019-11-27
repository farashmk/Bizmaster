package tech.stacka.bizmaster.utils

import com.github.mikephil.charting.formatter.ValueFormatter


class MyXAxisValueFormatter(private val mValues: ArrayList<String>) :
    ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return mValues[value.toInt()]
    }
}
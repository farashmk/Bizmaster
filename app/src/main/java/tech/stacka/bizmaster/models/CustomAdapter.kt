package tech.stacka.bizmaster.models

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_layout.view.*
import tech.stacka.bizmaster.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class CustomAdapter (val transactionsList:ArrayList<Transactions>):RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v=LayoutInflater.from(p0?.context).inflate(R.layout.list_layout,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return transactionsList.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val transactions=transactionsList[p1]
        p0?.textviewnotes.text=transactions.notes
        val sdf= SimpleDateFormat("dd\nMMM")
        println("$transactions.date")
        p0?.date.text=sdf.format(Date(transactions.date*1000))
        p0?.textMoney.text="₹${transactions.amount}"
        if (transactions.amountType){
            p0.textMoney.setTextColor(Color.GREEN)
        }else{
            p0.textMoney.setTextColor(Color.RED)
        }


    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textviewnotes=itemView.textViewnotes
        val textMoney=itemView.textViewmoney
        val date=itemView.tvdate



    }


}
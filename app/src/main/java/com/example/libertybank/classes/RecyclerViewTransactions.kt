package com.example.libertybank.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.libertybank.R

class RecyclerViewTransactions: RecyclerView.Adapter<RecyclerViewTransactions.ViewHolder>() {

    //    private var titles = arrayOf("Guess")
//    private val images = intArrayOf(R.drawable.samjer)
    private var titles = arrayOf("ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია","ტრანზაქცია")
    private var dates = arrayOf("2020/01/22","2020/01/22","2020/01/22","2020/01/22","2020/01/22","2020/01/22","2020/01/22","2020/01/22","2020/01/22")
    private var prices = arrayOf("200","200","200","200","200","200","200","200","200")
    private val images = intArrayOf(R.drawable.svg_transaction, R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction,R.drawable.svg_transaction)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewTransactions.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewTransactions.ViewHolder, position: Int) {
        holder.itemText.text = titles[position]
        holder.itemDate.text = dates[position]
        holder.itemPrice.text = prices[position]
        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return 1
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.imageViewTransactions)
        var itemText: TextView = itemView.findViewById(R.id.textViewTransactions)
        var itemDate: TextView = itemView.findViewById(R.id.textViewDate)
        var itemPrice: TextView = itemView.findViewById(R.id.textViewPrice)

//        init {
//
//
////            itemView.setOnClickListener {
//////                val position: Int = adapterPosition
//////                itemView.findNavController().navigate(R.id.action_quizHolderFragment_to_navigation_products)
//////                Toast.makeText(itemView.context, "egaa", Toast.LENGTH_SHORT).show()
////            }
//        }


    }
}
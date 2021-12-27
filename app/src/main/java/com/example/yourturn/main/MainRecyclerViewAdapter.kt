package com.example.yourturn.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourturn.R
import com.example.yourturn.data.Restriction
import com.example.yourturn.data.User

class MainRecyclerViewAdapter(
    private var items: List<User>,
    private val removeAction: (String) -> Unit
) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restriction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], removeAction)
    }

    override fun getItemCount() = items.size

    fun changeData(list: List<User>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var title: TextView
        private lateinit var desc: TextView
        private lateinit var amount: TextView

        fun bind(item: User, action: (String) -> Unit) {
            title = view.findViewById(R.id.title)
            desc = view.findViewById(R.id.description)
            amount = view.findViewById(R.id.amount)

            title.text = item.email
            desc.text = item.password
            amount.text = item._v.toString()
            view.findViewById<Button>(R.id.remove_button).setOnClickListener {
                action(item._id)
            }
        }

    }
}
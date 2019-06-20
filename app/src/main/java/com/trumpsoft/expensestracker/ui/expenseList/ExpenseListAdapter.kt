package com.trumpsoft.expensestracker.ui.expenseList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trumpsoft.expensestracker.R
import com.trumpsoft.expensestracker.domain.Expense
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_expense.view.*

class ExpenseListAdapter : RecyclerView.Adapter<ExpenseListAdapter.ViewHolder>() {
    var items = emptyList<Expense>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_expense,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView = itemView

        fun bind(expense: Expense) {
            containerView.tvDate.text = expense.dateTime.toString()
            containerView.tvSum.text = expense.value.toString()
            containerView.tvPlace.text = expense.place
            containerView.tvDescription.text = expense.description
        }
    }
}
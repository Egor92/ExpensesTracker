package com.trumpsoft.expensestracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_expense.view.*

class MainActivity : AppCompatActivity() {

    private val expenseListAdapter = ExpenseListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseList.adapter = expenseListAdapter
        expenseList.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(ExpenseListViewModel::class.java)

        viewModel.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::render)

        viewModel.input.accept(ExpenseListAction.LoadItems)
    }

    private fun render(state: ExpenseListState) {
        expenseListAdapter.items = state.items
    }
}

class ExpenseListAdapter : RecyclerView.Adapter<ExpenseListAdapter.ViewHolder>() {
    var items = emptyList<Expense>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView = itemView

        fun bind(expense: Expense) {
            containerView.tvDate.text = expense.dateTime.toString()
            containerView.tvSum.text = expense.value.toString()
            containerView.tvPlace.text = expense.place
            containerView.tvDescription.text = expense.description
        }
    }
}
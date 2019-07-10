package com.trumpsoft.expensestracker.ui.expenseList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.trumpsoft.expensestracker.R
import com.trumpsoft.expensestracker.application.ServiceLocator
import com.trumpsoft.expensestracker.presentation.expenseList.ExpenseListAction
import com.trumpsoft.expensestracker.presentation.expenseList.ExpenseListState
import com.trumpsoft.expensestracker.presentation.expenseList.ExpenseListViewModel
import com.trumpsoft.expensestracker.ui.expenseDetails.ExpenseDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_expense_list.*

class ExpenseListActivity : AppCompatActivity() {

    private val expenseListAdapter = ExpenseListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        expenseList.adapter = expenseListAdapter
        expenseList.layoutManager = LinearLayoutManager(this)

        addExpenseButton.setOnClickListener {
            val intent = Intent(this, ExpenseDetailsActivity::class.java)
            startActivity(intent)
        }

        val viewModel = getViewModel {
            ExpenseListViewModel(ServiceLocator.expenseRepository)
        }

        viewModel.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::render)

        viewModel.input.accept(ExpenseListAction.LoadItems)
    }

    private fun render(state: ExpenseListState) {
        expenseListAdapter.items = state.items
    }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(crossinline factory: () -> T): T {
    return ViewModelProvider(
        viewModelStore,
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
                @Suppress("UNCHECKED_CAST")
                return factory() as VM
            }
        }
    ).get(T::class.java)
}

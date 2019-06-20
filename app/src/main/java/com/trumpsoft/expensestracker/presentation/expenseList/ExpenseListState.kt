package com.trumpsoft.expensestracker.presentation.expenseList

import com.trumpsoft.expensestracker.domain.Expense

data class ExpenseListState(
    val items: List<Expense> = emptyList()
) {
    fun reduce(action: ExpenseListAction): ExpenseListState {
        return when (action) {
            is ExpenseListAction.ItemsLoaded -> this.copy(action.items)
            else -> this
        }
    }
}
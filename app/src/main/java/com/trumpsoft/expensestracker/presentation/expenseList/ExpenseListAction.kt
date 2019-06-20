package com.trumpsoft.expensestracker.presentation.expenseList

import com.trumpsoft.expensestracker.domain.Expense

sealed class ExpenseListAction {
    object LoadItems : ExpenseListAction()

    data class ItemsLoaded(
        val items: List<Expense>
    ) : ExpenseListAction()
}
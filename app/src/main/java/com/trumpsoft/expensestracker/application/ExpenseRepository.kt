package com.trumpsoft.expensestracker.application

import com.trumpsoft.expensestracker.domain.Expense

interface ExpenseRepository{
    fun saveExpense(expense: Expense)
    fun getAllExpenses() : List<Expense>
    fun getExpense(id: String) : Expense?
}


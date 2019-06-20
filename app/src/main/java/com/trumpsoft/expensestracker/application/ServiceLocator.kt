package com.trumpsoft.expensestracker.application

object ServiceLocator {
    val expenseRepository: ExpenseRepository = MemoryExpenseRepository()
}
package com.trumpsoft.expensestracker.application

import com.trumpsoft.expensestracker.domain.Expense
import java.util.*

class MemoryExpenseRepository : ExpenseRepository {
    private val expenses = mutableListOf<Expense>(
        Expense(
            id = UUID.randomUUID().toString(),
            value = 36.toBigDecimal(),
            dateTime = Date(1945, 5, 9),
            place = "SPAR",
            description = "Хлеб"
        ),
        Expense(
            id = UUID.randomUUID().toString(),
            value = 25000.toBigDecimal(),
            dateTime = Date(2016, 7, 26),
            place = "Sportmaster",
            description = "Электросамокат"
        ),
        Expense(
            id = UUID.randomUUID().toString(),
            value = 150.toBigDecimal(),
            dateTime = Date(2018, 11, 10),
            place = "Пятёрочка",
            description = "Масло"
        ),
        Expense(
            id = UUID.randomUUID().toString(),
            value = 3900.toBigDecimal(),
            dateTime = Date(2019, 3, 2),
            place = "Moloko Kafe",
            description = "Обед"
        )
    )

    override fun saveExpense(expense: Expense) {
        expenses.add(expense)
    }

    override fun getAllExpenses(): List<Expense> {
        return expenses.toList()
    }

    override fun getExpense(id: String): Expense? {
        return expenses.find { x -> x.id == id }
    }

}
package com.trumpsoft.expensestracker.domain

import java.math.BigDecimal
import java.util.*

data class Expense(
    val id: String,
    val value: BigDecimal,
    val dateTime: Date,
    val place: String,
    val description: String
) {

}

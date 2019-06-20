package com.trumpsoft.expensestracker

import java.math.BigDecimal
import java.util.*

data class Expense(
    val value: BigDecimal,
    val dateTime: Date,
    val place: String,
    val description: String
) {

}

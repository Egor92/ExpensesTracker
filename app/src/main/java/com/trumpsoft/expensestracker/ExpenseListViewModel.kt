package com.trumpsoft.expensestracker

import androidx.lifecycle.ViewModel
import com.freeletics.rxredux.reduxStore
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import java.util.*

/*
abstract class ReduxViewModel<S:Any, A:Any> : ViewModel() {
    protected val inputRelay = PublishRelay.create<ExpenseListAction>()

    val input : Consumer<ExpenseListAction> = inputRelay

    abstract val state: Observable<ExpenseListState>
}
*/

class ExpenseListViewModel : ViewModel() {
    protected val inputRelay = PublishRelay.create<ExpenseListAction>()

    val input: Consumer<ExpenseListAction> = inputRelay

    val state: Observable<ExpenseListState> = inputRelay.reduxStore(
        initialState = ExpenseListState(),
        sideEffects = listOf(),
        reducer = ExpenseListState::reduce
    )
}

data class ExpenseListState(
    val items: List<Expense> = emptyList()
) {
    fun reduce(action: ExpenseListAction): ExpenseListState {
        return when (action) {
            ExpenseListAction.LoadItems -> this.copy(
                items = listOf(
                    Expense(
                        value = 36.toBigDecimal(),
                        dateTime = Date(1945, 5, 9),
                        place = "SPAR",
                        description = "Хлеб"
                    ),
                    Expense(
                        value = 25000.toBigDecimal(),
                        dateTime = Date(2016, 7, 26),
                        place = "Sportmaster",
                        description = "Электросамокат"
                    ),
                    Expense(
                        value = 150.toBigDecimal(),
                        dateTime = Date(2018, 11, 10),
                        place = "Пятёрочка",
                        description = "Масло"
                    ),
                    Expense(
                        value = 3900.toBigDecimal(),
                        dateTime = Date(2019, 3, 2),
                        place = "Moloko Kafe",
                        description = "Обед"
                    )
                )
            )
        }
    }
}

sealed class ExpenseListAction {
    object LoadItems : ExpenseListAction()
}



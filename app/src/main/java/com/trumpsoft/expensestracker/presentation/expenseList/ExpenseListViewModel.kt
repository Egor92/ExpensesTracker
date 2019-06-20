package com.trumpsoft.expensestracker.presentation.expenseList

import androidx.lifecycle.ViewModel
import com.freeletics.rxredux.SideEffect
import com.freeletics.rxredux.reduxStore
import com.jakewharton.rxrelay2.PublishRelay
import com.trumpsoft.expensestracker.application.ExpenseRepository
import io.reactivex.Observable
import io.reactivex.functions.Consumer


class ExpenseListViewModel(
    val expenseRepository: ExpenseRepository
) : ViewModel() {
    private val inputRelay = PublishRelay.create<ExpenseListAction>()

    val input: Consumer<ExpenseListAction> = inputRelay

    val state: Observable<ExpenseListState> = inputRelay.reduxStore(
        initialState = ExpenseListState(),
        sideEffects = listOf(loadItems()),
        reducer = ExpenseListState::reduce
    )

    fun loadItems(): SideEffect<ExpenseListState, ExpenseListAction> {
        return { actions, state ->
            actions.ofType(ExpenseListAction.LoadItems::class.java)
                .map {
                    val expenses = expenseRepository.getAllExpenses()
                    ExpenseListAction.ItemsLoaded(expenses)
                }
        }

    }
}



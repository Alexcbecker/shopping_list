package com.domain.usecase

import com.domain.ShoppingList
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Observable

class CreateShoppingListUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Long, CreateShoppingListUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Long> {
        return shoppingListRepository.createShoppingList(params.shoppingList).toObservable()
    }

    data class Params(val shoppingList: ShoppingList)
}
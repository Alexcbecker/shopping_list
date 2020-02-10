package com.domain.usecase

import com.domain.ShoppingList
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Observable

class GetAllShoppingListsUseCase(
    private val shoppingRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<ShoppingList>, Unit>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Observable<List<ShoppingList>> {
        return shoppingRepository.getAllShoppingLists()
    }
}
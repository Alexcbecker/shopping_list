package com.domain.usecase

import com.domain.ShoppingList
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class AddItemsToShoppingListUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<AddItemsToShoppingListUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        return shoppingListRepository.addItemsToShoppingList(params.shoppingList)
    }

    data class Params(val shoppingList: ShoppingList)
}
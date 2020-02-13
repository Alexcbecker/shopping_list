package com.domain.usecase

import com.domain.GroceryItem
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class DeleteGroceryItemUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteGroceryItemUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        return shoppingListRepository.removeGroceryItemOfShoppingList(params.groceryItem, params.shoppingListId)
    }


    data class Params(
        val groceryItem: GroceryItem,
        val shoppingListId: Int
    )
}
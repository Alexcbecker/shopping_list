package com.domain.usecase

import com.domain.ShoppingList
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class EditShoppingListUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<EditShoppingListUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        return shoppingListRepository.editShoppingList(params.shoppingList)
    }


    data class Params(val shoppingList: ShoppingList)
}
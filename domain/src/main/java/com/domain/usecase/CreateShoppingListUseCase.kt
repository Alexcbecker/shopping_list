package com.domain.usecase

import com.domain.ShoppingList
import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class CreateShoppingListUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<CreateShoppingListUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        return shoppingListRepository.createShoppingList(params.shoppingList)
    }


    data class Params(val shoppingList: ShoppingList)
}
package com.domain.usecase

import com.domain.executor.PostExecutionThread
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class DeleteShoppingListUseCase(
    private val shoppingListRepository: IShoppingListRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteShoppingListUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        return shoppingListRepository.deleteShoppingList(params.id)
    }


    data class Params(val id: String)
}
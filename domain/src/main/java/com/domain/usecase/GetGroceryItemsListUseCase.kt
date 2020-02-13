package com.domain.usecase

import com.domain.GroceryItem
import com.domain.executor.PostExecutionThread
import com.domain.repository.IGroceryItemsRepository
import io.reactivex.Observable

class GetGroceryItemsListUseCase(
    private val groceryItemsRepository: IGroceryItemsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<GroceryItem>, Unit>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit): Observable<List<GroceryItem>> {
        return groceryItemsRepository.getGroceryItemsList()
    }
}
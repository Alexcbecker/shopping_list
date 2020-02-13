package com.data

import com.domain.GroceryItem
import com.domain.repository.IGroceryItemsRepository
import io.reactivex.Observable

class GroceryItemsRepository(private val groceryItemsRemoteDataSource: IGroceryItemsRemoteDataSource) :
    IGroceryItemsRepository {

    override fun getGroceryItemsList(): Observable<List<GroceryItem>> {
        return groceryItemsRemoteDataSource.getGroceryItemsList()
    }
}
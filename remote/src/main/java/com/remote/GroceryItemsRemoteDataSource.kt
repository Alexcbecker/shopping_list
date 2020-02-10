package com.remote

import com.data.IGroceryItemsRemoteDataSource
import com.domain.GroceryItem
import com.remote.mapper.toDomain
import com.remote.service.GroceryItemsService
import io.reactivex.Observable
import java.math.BigInteger

class GroceryItemsRemoteDataSource(private val groceryItemsService: GroceryItemsService) :
    IGroceryItemsRemoteDataSource {

    override fun getGroceryItemsList(): Observable<List<GroceryItem>> {
//        return groceryItemsService.getGroceryItems()
//            .map { groceryItemsList ->
//                groceryItemsList.map(GroceryItemEntity::toDomain)
//            }
        return Observable.just(mockGroceryItemList()
            .map { groceryItemsList ->
                groceryItemsList.toDomain()
            })
    }

    private fun mockGroceryItemList(): List<GroceryItemEntity> {
        val groceryList = listOf<GroceryItemEntity>().toMutableList()
        for (x in 0..10) {
            groceryList.add(mockGroceryItem(index = x))
        }
        return groceryList
    }

    private fun mockGroceryItem(index: Int): GroceryItemEntity {
        return GroceryItemEntity(
            id = index,
            name = "Mock item $index",
            price = BigInteger.valueOf(index.toLong())
        )
    }
}
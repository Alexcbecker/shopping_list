package com.local

import androidx.paging.DataSource
import com.data.IShoppingListLocalDataSource
import com.domain.ShoppingList
import com.local.dao.GroceryItemDao
import com.local.dao.ShoppingListDao
import com.local.mapper.fromDomain
import com.local.mapper.toDomain
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ShoppingListLocalDataSource(
    private val shoppingListDao: ShoppingListDao,
    private val groceryItemDao: GroceryItemDao
) : IShoppingListLocalDataSource {

    override fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListDao.findAll().map { it.toDomain() }
    }

    override fun createShoppingList(shoppingList: ShoppingList): Single<Long> {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        return shoppingListDao.insert(shoppingListWithGroceryItems.shoppingList)
    }

    override fun addItemsToShoppingList(shoppingList: ShoppingList): Completable {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        shoppingListWithGroceryItems.groceryItemList?.map { it.shoppingListId = shoppingList.id }
        return Observable.fromIterable(shoppingListWithGroceryItems.groceryItemList)
            .concatMapCompletable {
                groceryItemDao.insert(it)
            }
    }

    override fun editShoppingList(shoppingList: ShoppingList): Completable {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        val editGroceries = Observable.fromIterable(shoppingListWithGroceryItems.groceryItemList)
            .concatMapCompletable {
                groceryItemDao.update(it)
            }
        return shoppingListDao.edit(shoppingListWithGroceryItems.shoppingList)
            .andThen(editGroceries)
    }

    override fun deleteShoppingList(id: String): Completable {
        return shoppingListDao.deleteById(id.toLong())
    }
}
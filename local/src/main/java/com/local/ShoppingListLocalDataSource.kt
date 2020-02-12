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

class ShoppingListLocalDataSource(
    private val shoppingListDao: ShoppingListDao,
    private val groceryItemDao: GroceryItemDao
) : IShoppingListLocalDataSource {


    override fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListDao.findAll().map { it.toDomain() }
    }

    override fun createShoppingList(shoppingList: ShoppingList): Completable {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        val saveGroceries = Observable.fromIterable(shoppingListWithGroceryItems.groceryItemList)
            .concatMapCompletable {
                groceryItemDao.insert(it)
            }
        return shoppingListDao.insert(shoppingListWithGroceryItems.shoppingList)
            .andThen(saveGroceries)
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
        return shoppingListDao.deleteById(id)
    }
}
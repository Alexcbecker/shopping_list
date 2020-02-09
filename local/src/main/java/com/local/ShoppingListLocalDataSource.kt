package com.local

import com.data.IShoppingListLocalDataSource
import com.domain.ShoppingList
import com.local.dao.GroceryItemDao
import com.local.dao.ShoppingListDao
import com.local.entity.ShoppingListWithGroceryItems
import com.local.mapper.fromDomain
import com.local.mapper.toDomain
import io.reactivex.Completable
import io.reactivex.Observable

class ShoppingListLocalDataSource(
    private val shoppingListDao: ShoppingListDao,
    private val groceryItemDao: GroceryItemDao
) : IShoppingListLocalDataSource {

    override fun getAllShoppingLists(): Observable<List<ShoppingList>> {
        return shoppingListDao.findAll()
            .map { shoppingList ->
                shoppingList.map(ShoppingListWithGroceryItems::toDomain)
            }
    }

    override fun createShoppingList(shoppingList: ShoppingList): Completable {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        return shoppingListDao.insert(shoppingListWithGroceryItems.shoppingList)
            .andThen {
                Observable.fromIterable(shoppingListWithGroceryItems.groceryItemList).concatMapCompletable {
                    groceryItemDao.insert(it)
                }
            }
    }

    override fun editShoppingList(shoppingList: ShoppingList): Completable {
        val shoppingListWithGroceryItems = shoppingList.fromDomain()
        return shoppingListDao.edit(shoppingListWithGroceryItems.shoppingList)
            .andThen {
                Observable.fromIterable(shoppingListWithGroceryItems.groceryItemList).concatMapCompletable {
                    groceryItemDao.update(it)
                }
            }
    }

    override fun deleteShoppingList(id: String): Completable {
        return shoppingListDao.deleteById(id)
    }
}
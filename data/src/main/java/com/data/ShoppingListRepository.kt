package com.data

import androidx.paging.DataSource
import com.domain.GroceryItem
import com.domain.ShoppingList
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ShoppingListRepository(private val shoppingListLocalDataSource: IShoppingListLocalDataSource) :
    IShoppingListRepository {

    override fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListLocalDataSource.getAllShoppingLists()
    }

    override fun createShoppingList(shoppingList: ShoppingList): Single<Long> {
        return shoppingListLocalDataSource.createShoppingList(shoppingList)
    }

    override fun addItemsToShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListLocalDataSource.addItemsToShoppingList(shoppingList)
    }

    override fun editShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListLocalDataSource.editShoppingList(shoppingList)
    }

    override fun deleteShoppingList(id: String): Completable {
        return shoppingListLocalDataSource.deleteShoppingList(id)
    }

    override fun removeGroceryItemOfShoppingList(groceryItem: GroceryItem, shoppingLIstId: Int): Completable {
        return shoppingListLocalDataSource.removeGroceryItemOfShoppingList(groceryItem, shoppingLIstId)
    }
}
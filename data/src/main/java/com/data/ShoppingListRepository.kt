package com.data

import androidx.paging.DataSource
import com.domain.ShoppingList
import com.domain.repository.IShoppingListRepository
import io.reactivex.Completable

class ShoppingListRepository(private val shoppingListLocalDataSource: IShoppingListLocalDataSource) :
    IShoppingListRepository {

    override fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList> {
        return shoppingListLocalDataSource.getAllShoppingLists()
    }

    override fun createShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListLocalDataSource.createShoppingList(shoppingList)
    }

    override fun editShoppingList(shoppingList: ShoppingList): Completable {
        return shoppingListLocalDataSource.editShoppingList(shoppingList)
    }

    override fun deleteShoppingList(id: String): Completable {
        return shoppingListLocalDataSource.deleteShoppingList(id)
    }
}
package com.domain.repository

import androidx.paging.DataSource
import com.domain.GroceryItem
import com.domain.ShoppingList
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IShoppingListRepository {

    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun createShoppingList(shoppingList: ShoppingList): Single<Long>
    fun addItemsToShoppingList(shoppingList: ShoppingList): Completable
    fun removeGroceryItemOfShoppingList(groceryItem: GroceryItem, shoppingLIstId: Int): Completable
    fun editShoppingList(shoppingList: ShoppingList): Completable
    fun deleteShoppingList(id: String): Completable
}
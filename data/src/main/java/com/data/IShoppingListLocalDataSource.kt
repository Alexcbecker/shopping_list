package com.data

import androidx.paging.DataSource
import com.domain.ShoppingList
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IShoppingListLocalDataSource {

    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun createShoppingList(shoppingList: ShoppingList): Single<Long>
    fun addItemsToShoppingList(shoppingList: ShoppingList): Completable
    fun editShoppingList(shoppingList: ShoppingList): Completable
    fun deleteShoppingList(id: String): Completable
}
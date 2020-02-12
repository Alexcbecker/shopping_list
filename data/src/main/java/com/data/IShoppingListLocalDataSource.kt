package com.data

import androidx.paging.DataSource
import com.domain.ShoppingList
import io.reactivex.Completable
import io.reactivex.Observable

interface IShoppingListLocalDataSource {

    fun getAllShoppingLists(): DataSource.Factory<Int, ShoppingList>
    fun createShoppingList(shoppingList: ShoppingList): Completable
    fun editShoppingList(shoppingList: ShoppingList): Completable
    fun deleteShoppingList(id: String): Completable
}
package com.domain.repository

import com.domain.ShoppingList
import io.reactivex.Completable
import io.reactivex.Observable

interface IShoppingListRepository {

    fun getAllShoppingLists(): Observable<List<ShoppingList>>
    fun createShoppingList(shoppingList: ShoppingList): Completable
    fun editShoppingList(shoppingList: ShoppingList): Completable
    fun deleteShoppingList(id: String): Completable
}
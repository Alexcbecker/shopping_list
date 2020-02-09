package com.local.dao

import androidx.room.*
import com.local.entity.ShoppingList
import com.local.entity.ShoppingListWithGroceryItems
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: ShoppingList): Completable

    @Update
    fun edit(shoppingList: ShoppingList): Completable

    @Query("DELETE FROM ShoppingList WHERE id = :id")
    fun deleteById(id: String): Completable

    @Query("SELECT * FROM ShoppingList")
    @Transaction
    fun findAll(): Observable<List<ShoppingListWithGroceryItems>>
}
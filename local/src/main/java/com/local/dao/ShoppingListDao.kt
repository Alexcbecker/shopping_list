package com.local.dao

import androidx.paging.DataSource
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

    @Query("SELECT * FROM ShoppingList ORDER BY date DESC")
    @Transaction
    //fun findAll(): Observable<List<ShoppingListWithGroceryItems>>
    fun findAll(): DataSource.Factory<Int, ShoppingListWithGroceryItems>
}
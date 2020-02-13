package com.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.local.entity.ShoppingList
import com.local.entity.ShoppingListWithGroceryItems
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: ShoppingList): Single<Long>

    @Update
    fun edit(shoppingList: ShoppingList): Completable

    @Query("DELETE FROM ShoppingList WHERE id = :id")
    fun deleteById(id: Long): Completable

    @Query("SELECT * FROM ShoppingList ORDER BY date DESC")
    @Transaction
    fun findAll(): DataSource.Factory<Int, ShoppingListWithGroceryItems>
}
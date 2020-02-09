package com.local.dao

import androidx.room.*
import com.local.entity.GroceryItem
import io.reactivex.Completable

@Dao
interface GroceryItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(groceryItem: GroceryItem): Completable

    @Update
    fun update(groceryItem: GroceryItem): Completable
}
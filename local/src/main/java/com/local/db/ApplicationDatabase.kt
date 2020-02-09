package com.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.local.dao.GroceryItemDao
import com.local.dao.ShoppingListDao
import com.local.entity.GroceryItem
import com.local.entity.ShoppingList

@Database(
    entities = [ShoppingList::class, GroceryItem::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun groceryItemDao(): GroceryItemDao
}
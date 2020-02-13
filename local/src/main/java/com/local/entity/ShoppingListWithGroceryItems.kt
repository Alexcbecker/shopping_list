package com.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingListWithGroceryItems(
    @Embedded val shoppingList: ShoppingList,
    @Relation(parentColumn = "id", entityColumn = "shoppingListId", entity = GroceryItem::class)
    val groceryItemList: List<GroceryItem>? = null
)
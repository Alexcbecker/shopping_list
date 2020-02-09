package com.local.entity

import androidx.room.PrimaryKey
import java.math.BigInteger

data class GroceryItem(
    @PrimaryKey(autoGenerate = true)
    val id: String? = null,
    val shoppingListId: String? = null,
    val name: String,
    val price: BigInteger,
    val quantity: Int
)
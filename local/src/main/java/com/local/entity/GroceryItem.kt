package com.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity
data class GroceryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val shoppingListId: Int? = null,
    val name: String,
    val price: BigInteger,
    val quantity: Int
)
package com.domain

import java.util.Date

data class ShoppingList(
    val id: String? = null,
    val date: Date = Date(),
    val name: String,
    val items :List<GroceryItem>? = null
)
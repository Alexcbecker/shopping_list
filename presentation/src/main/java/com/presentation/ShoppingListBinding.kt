package com.presentation

import com.presentation.extension.toBrazilString
import java.util.Date

data class ShoppingListBinding(
    val id: String? = null,
    val date: Date = Date(),
    val name: String,
    val items: List<GroceryItemBinding>? = null
) {
    val dateFormatted = date.toBrazilString()
}
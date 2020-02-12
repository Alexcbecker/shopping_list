package com.presentation.mapper

import com.domain.GroceryItem
import com.domain.ShoppingList
import com.presentation.GroceryItemBinding
import com.presentation.ShoppingListBinding

fun ShoppingList.fromDomain(): ShoppingListBinding {
    return ShoppingListBinding(
        id = this.id?.toString(),
        date = this.date,
        name = this.name,
        items = this.items?.map(GroceryItem::fromDomain) ?: mutableListOf()
    )
}

fun ShoppingListBinding.toDomain(): ShoppingList {
    return ShoppingList(
        id = this.id?.toInt(),
        date = this.date,
        name = this.name ?: "",
        items = this.items.map(GroceryItemBinding::toDomain)
    )
}
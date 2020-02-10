package com.presentation.mapper

import com.domain.GroceryItem
import com.presentation.GroceryItemBinding

fun GroceryItem.fromDomain(): GroceryItemBinding {
    return GroceryItemBinding(
        id = this.id,
        name = this.name,
        price = this.price,
        quantity = this.quantity
    )
}

fun GroceryItemBinding.toDomain(): GroceryItem {
    return GroceryItem(
        id = this.id,
        name = this.name,
        price = this.price,
        quantity = this.quantity
    )
}
package com.presentation.mapper

import com.domain.GroceryItem
import com.presentation.GroceryItemBinding

fun GroceryItem.fromDomain(): GroceryItemBinding {
    return GroceryItemBinding(
        id = this.id?.toString(),
        name = this.name,
        price = this.price,
        quantity = if (this.quantity == 0) "" else this.quantity.toString()
    )
}

fun GroceryItemBinding.toDomain(): GroceryItem {
    return GroceryItem(
        id = this.id?.toInt(),
        name = this.name,
        price = this.price,
        quantity = this.quantity.toInt()
    )
}
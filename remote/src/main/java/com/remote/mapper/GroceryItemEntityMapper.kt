package com.remote.mapper

import com.domain.GroceryItem
import com.remote.GroceryItemEntity

fun GroceryItemEntity.toDomain(): GroceryItem {
    return GroceryItem(
        id = this.id,
        name = this.name,
        price = this.price
    )
}
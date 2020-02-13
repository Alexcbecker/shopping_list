package com.local.mapper

import com.domain.GroceryItem
import com.domain.ShoppingList
import com.local.entity.ShoppingListWithGroceryItems
import com.local.entity.ShoppingList as ShoppingListEntity
import com.local.entity.GroceryItem as GroceryItemEntity

fun ShoppingListWithGroceryItems.toDomain(): ShoppingList {
    return ShoppingList(
        id = this.shoppingList.id,
        date = this.shoppingList.date,
        name = this.shoppingList.name,
        items = this.groceryItemList?.map { item ->
            GroceryItem(
                id = item.id,
                name = item.name,
                price = item.price,
                quantity = item.quantity
            )
        }
    )
}

fun ShoppingList.fromDomain(): ShoppingListWithGroceryItems {
    return ShoppingListWithGroceryItems(
        shoppingList = ShoppingListEntity(
            id = this.id ?: 0,
            date = this.date,
            name = this.name
        ),
        groceryItemList = this.items?.map { groceryItem ->
            GroceryItemEntity(
                id = groceryItem.id ?: 0,
                shoppingListId = this.id,
                name = groceryItem.name,
                price = groceryItem.price,
                quantity = groceryItem.quantity
            )
        }
    )
}

fun GroceryItem.fromDomain(shoppingListId: Int): GroceryItemEntity {
    return GroceryItemEntity(
        id = this.id ?: 0,
        shoppingListId = shoppingListId,
        name = this.name,
        price = this.price,
        quantity = this.quantity
    )
}
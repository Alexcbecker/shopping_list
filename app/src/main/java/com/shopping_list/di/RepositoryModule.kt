package com.shopping_list.di

import com.data.GroceryItemsRepository
import com.data.ShoppingListRepository
import com.domain.repository.IGroceryItemsRepository
import com.domain.repository.IShoppingListRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<IShoppingListRepository> {
        ShoppingListRepository(get())
    }

    single<IGroceryItemsRepository> {
        GroceryItemsRepository(get())
    }
}
package com.shopping_list.di

import com.data.ShoppingListRepository
import com.domain.repository.IShoppingListRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IShoppingListRepository> {
        ShoppingListRepository(get())
    }
}
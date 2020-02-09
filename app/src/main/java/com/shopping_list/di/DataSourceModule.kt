package com.shopping_list.di

import com.data.IShoppingListLocalDataSource
import com.local.ShoppingListLocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<IShoppingListLocalDataSource> { ShoppingListLocalDataSource(get(), get()) }

}
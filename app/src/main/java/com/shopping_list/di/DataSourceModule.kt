package com.shopping_list.di

import com.data.IGroceryItemsRemoteDataSource
import com.data.IShoppingListLocalDataSource
import com.local.ShoppingListLocalDataSource
import com.remote.GroceryItemsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<IShoppingListLocalDataSource> { ShoppingListLocalDataSource(get(), get()) }

    single<IGroceryItemsRemoteDataSource> { GroceryItemsRemoteDataSource(get()) }
}
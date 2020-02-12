package com.shopping_list.di

import com.presentation.viewModel.CreateShoppingListViewModel
import com.presentation.viewModel.GetGroceryItemsListViewModel
import com.presentation.viewModel.ShoppingListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        CreateShoppingListViewModel(androidApplication(), get())
    }

    viewModel {
        GetGroceryItemsListViewModel(androidApplication(), get())
    }

    viewModel {
        ShoppingListViewModel(androidApplication(), get())
    }
}
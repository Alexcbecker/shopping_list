package com.shopping_list.di

import com.presentation.viewModel.ManageShoppingListViewModel
import com.presentation.viewModel.DeleteShoppingListViewModel
import com.presentation.viewModel.GetGroceryItemsListViewModel
import com.presentation.viewModel.ShoppingListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        ManageShoppingListViewModel(androidApplication(), get())
    }

    viewModel {
        GetGroceryItemsListViewModel(androidApplication(), get())
    }

    viewModel {
        ShoppingListViewModel(androidApplication(), get())
    }

    viewModel {
        DeleteShoppingListViewModel(androidApplication(), get())
    }
}
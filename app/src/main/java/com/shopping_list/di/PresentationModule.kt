package com.shopping_list.di

import com.presentation.viewModel.CreateShoppingListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        CreateShoppingListViewModel(androidApplication(), get())
    }
}
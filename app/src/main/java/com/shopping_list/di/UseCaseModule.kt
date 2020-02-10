package com.shopping_list.di

import com.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    single {
        GetAllShoppingListsUseCase(get(), get())
    }

    single {
        CreateShoppingListUseCase(get(), get())
    }

    single {
        DeleteShoppingListUseCase(get(), get())
    }

    single {
        EditShoppingListUseCase(get(), get())
    }

    single {
        GetGroceryItemsListUseCase(get(), get())
    }
}
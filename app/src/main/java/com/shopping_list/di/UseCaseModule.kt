package com.shopping_list.di

import com.domain.usecase.CreateShoppingListUseCase
import com.domain.usecase.DeleteShoppingListUseCase
import com.domain.usecase.EditShoppingListUseCase
import com.domain.usecase.GetAllShoppingListsUseCase
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
}
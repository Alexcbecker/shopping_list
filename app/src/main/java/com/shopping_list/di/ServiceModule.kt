package com.shopping_list.di

import com.remote.service.GroceryItemsService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    factory {
        createWebService<GroceryItemsService>(get())
    }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}
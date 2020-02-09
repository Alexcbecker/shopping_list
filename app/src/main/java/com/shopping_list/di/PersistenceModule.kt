package com.shopping_list.di

import androidx.room.Room
import com.local.db.ApplicationDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(), ApplicationDatabase::class.java, "shoppingListDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        (get() as ApplicationDatabase).shoppingListDao()
    }

    single {
        (get() as ApplicationDatabase).groceryItemDao()
    }
}
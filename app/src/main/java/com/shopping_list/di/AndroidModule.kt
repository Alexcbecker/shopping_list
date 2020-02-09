package com.shopping_list.di

import com.domain.executor.PostExecutionThread
import com.shopping_list.ui.executor.UiThread
import org.koin.dsl.module

val androidModule = module {
    single { this }
    single { UiThread() as PostExecutionThread }
}
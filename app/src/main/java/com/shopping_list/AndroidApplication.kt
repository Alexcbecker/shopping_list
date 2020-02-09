package com.shopping_list

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.shopping_list.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        startKoin {
            androidContext(this@AndroidApplication)
            modules(listOf(
                androidModule
            ))
        }
        configTimber()
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ':'.toString() + element.lineNumber
                }
            })
        }
    }
}
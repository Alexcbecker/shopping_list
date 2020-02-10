package com.simplus.remote.config

import com.remote.config.RxCallAdapter
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxCallAdapterFactory private constructor() : Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        fun create(): CallAdapter.Factory =
            RxCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        val adapter = original.get(returnType, annotations, retrofit)

        if (rawType == Completable::class.java) {
            return RxCallAdapter<Completable>(
                retrofit,
                adapter,
                rawType
            )
        }

        if (rawType != Observable::class.java) return null

        return RxCallAdapter<Observable<*>>(
            retrofit,
            adapter,
            rawType
        )
    }
}

package com.remote.config

import com.remote.exception.*
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException

class RxCallAdapter<T>(
    private val retrofit: Retrofit,
    private val wrapped: CallAdapter<*, *>?,
    private val ramType: Class<*>
) : CallAdapter<T, Any> {

    override fun responseType(): Type? {
        return wrapped?.responseType()
    }

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<T>): Any {
        if (ramType == Completable::class.java) {
            return (wrapped as CallAdapter<T, Completable>).adapt(call)
                    .onErrorResumeNext { throwable ->
                        Completable.error(asException(throwable))
                    }
        }
        return (wrapped as CallAdapter<T, Observable<Any>>)
                .adapt(call)
                .onErrorResumeNext { t: Throwable -> Observable.error(asException(t)) }
    }

    private fun asException(throwable: Throwable): Exception {
        return when (throwable) {
            is HttpException -> when (throwable.code()) {
                400, 422 -> BadRequestException(retrofit, throwable.response()?.errorBody())
                404 -> NotFoundHttpException(retrofit, throwable.response()?.errorBody())
                500, 504 -> InternalServerErrorException()
                else -> UnknownException(throwable)
            }
            is ConnectException -> InternalServerErrorException()
            is IOException -> NetworkConnectionException()
            else -> UnknownException(throwable)
        }
    }
}
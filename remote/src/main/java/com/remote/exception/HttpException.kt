package com.remote.exception

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.Serializable

abstract class HttpException
constructor(
    private val retrofit: Retrofit,
    private val responseBody: ResponseBody?
) : Exception() {

    private val responseError: ResponseError?

    init {
        responseError = getErrorBody()
    }

    private fun getErrorBody(): ResponseError? {
        if (responseBody == null) return null
        return generateConverter().convert(responseBody)
    }

    private fun generateConverter(): Converter<ResponseBody, ResponseError> {
        return retrofit.responseBodyConverter<ResponseError>(ResponseError::class.java, arrayOfNulls<Annotation>(0))
    }

    fun message(): String {
        if (responseError == null) return ""
        return responseError.error.message
    }

    internal data class ResponseError(@SerializedName("error") val error: Error) : Serializable

    internal data class Error(
        @SerializedName("message") val message: String,
        @SerializedName("detail") val detail: ErrorDetail
    ) : Serializable

    internal data class ErrorDetail(
        @SerializedName("code") val code: Int,
        @SerializedName("type") val type: String
    ) : Serializable
}
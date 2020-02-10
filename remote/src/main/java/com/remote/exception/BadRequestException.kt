package com.remote.exception

import okhttp3.ResponseBody
import retrofit2.Retrofit

class BadRequestException(retrofit: Retrofit, responseBody: ResponseBody?) : HttpException(retrofit, responseBody)

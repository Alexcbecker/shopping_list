package com.remote.exception

class NetworkConnectionException : Exception {
    constructor() : super()
    constructor(ex: Throwable?) : super(ex)
}
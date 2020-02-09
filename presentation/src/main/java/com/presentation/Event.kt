package com.presentation

open class Event<out D>(private val data: D) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): D? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }
}

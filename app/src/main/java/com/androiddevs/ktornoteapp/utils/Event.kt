package com.androiddevs.ktornoteapp.utils

// out - allows Covariance
// Enables you to use a more derived type than originally specified.
// example if T is from Number it is possible to assign Int that is derived from Number
open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set // allow changing from within this class but not outside

    fun getContentIfNotHandled() = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    fun peekContent() = content
}
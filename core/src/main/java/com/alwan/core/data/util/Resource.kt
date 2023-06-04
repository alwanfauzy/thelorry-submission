package com.alwan.core.data.util

sealed class Resource<T>(val data: T?  = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}

fun <T : Any> Resource<T>.onLoading(
    executable: (T?) -> Unit
): Resource<T> = apply {
    if (this is Resource.Loading<T>) {
        executable(data)
    }
}

fun <T : Any> Resource<T>.onSuccess(
    executable: (T?) -> Unit
): Resource<T> = apply {
    if (this is Resource.Success<T>) {
        executable(data)
    }
}

fun <T : Any> Resource<T>.onError(
    executable: (message: String?, data: T?) -> Unit
): Resource<T> = apply {
    if (this is Resource.Error<T>) {
        executable(message, data)
    }
}
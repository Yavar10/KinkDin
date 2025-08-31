package com.mkj.kinkdin.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> error(msg: String, data: T? = null): Resource<T> = Error(msg, data)

        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
    }

    val isLoading: Boolean
        get() = this is Loading

    val isSuccess: Boolean
        get() = this is Success

    val isError: Boolean
        get() = this is Error
}

// Extension functions for easier handling
inline fun <T> Resource<T>.onSuccess(action: (value: T) -> Unit): Resource<T> {
    if (this is Resource.Success && data != null) action(data)
    return this
}

inline fun <T> Resource<T>.onError(action: (exception: String?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message)
    return this
}

inline fun <T> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (this is Resource.Loading) action()
    return this
}

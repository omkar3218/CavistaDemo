package com.omkar.cavistademo.data.remote


sealed class Resource<T>(
    val data: T? = null,
    val error: AppError? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class DataError<T>(error: AppError) : Resource<T>(null, error)
}

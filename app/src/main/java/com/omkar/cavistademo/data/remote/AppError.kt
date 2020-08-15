package com.omkar.cavistademo.data.remote


class AppError(val code: Int, val description: String) {

    companion object {
        const val NO_INTERNET_CONNECTION = -1
        const val NETWORK_ERROR = -2
    }
}

package com.asterisk.tuandao.themoviedb.data.source.remote

sealed class Resources<T> {
    data class Progress<T>(var loading: Boolean) : Resources<T>()
    data class Success<T>(var data: T) : Resources<T>()
    data class Failure<T>(val e: Throwable) : Resources<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Resources<T> = Progress(isLoading)

        fun <T> success(data: T): Resources<T> = Success(data)

        fun <T> failure(e: Throwable): Resources<T> = Failure(e)
    }
}

package com.example.galaxyonenative.data.network



import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class ResultNetwork<out T> {

    data class Success<out T>(val data: T) : ResultNetwork<T>()
    data class Failure(val msg: Throwable?) : ResultNetwork<Nothing>()
    object Loading : ResultNetwork<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success $data"
            is Failure -> "Failure $msg"
            Loading -> "Loading"
        }
    }
}

fun <T, R> ResultNetwork<T>.map(transform: (T) -> R): ResultNetwork<R> {
    return when (this) {
        is ResultNetwork.Success -> ResultNetwork.Success(transform(data))
        is ResultNetwork.Failure -> ResultNetwork.Failure(msg)
        ResultNetwork.Loading -> ResultNetwork.Loading
    }
}

fun <T> Flow<ResultNetwork<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<ResultNetwork<T>> =
    transform { result ->
        if (result is ResultNetwork.Success) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<ResultNetwork<T>>.doOnFailure(action: suspend (Throwable?) -> Unit): Flow<ResultNetwork<T>> =
    transform { result ->
        if (result is ResultNetwork.Failure) {
            action(result.msg)
        }
        return@transform emit(result)
    }

fun <T> Flow<ResultNetwork<T>>.doOnLoading(action: suspend () -> Unit): Flow<ResultNetwork<T>> =
    transform { result ->
        if (result is ResultNetwork.Loading) {
            action()
        }
        return@transform emit(result)
    }


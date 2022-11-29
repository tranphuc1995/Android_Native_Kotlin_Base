package com.example.galaxyonenative.data.network


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> Response<T>
    ): Flow<ResultNetwork<T>> = flow {
        emit(ResultNetwork.Loading)
        val response = apiCall()
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                emit(ResultNetwork.Success(data))
            } else {
                val error = response.errorBody()
                if (error != null) {
                    emit(ResultNetwork.Failure(IOException(error.toString())))
                } else {
                    emit(ResultNetwork.Failure(IOException("something went wrong")))
                }
            }
        } else {
            emit(ResultNetwork.Failure(Throwable(response.errorBody().toString())))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(ResultNetwork.Failure(Exception(e)))
    }.flowOn(dispatcher)

}
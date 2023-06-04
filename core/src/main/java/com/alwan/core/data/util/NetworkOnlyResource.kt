package com.alwan.core.data.util

import kotlinx.coroutines.flow.*

abstract class NetworkOnlyResource<ResultType, RequestType : Any> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val apiResponse = createCall().first()
        apiResponse.onSuccess { data ->
            emitAll(loadFromNetwork(data).map {
                Resource.Success(it)
            })
        }.onError {message ->
            emit(Resource.Error(message))
        }.onException { e ->
            val message = "Exception: ${e.message}"

            emit(Resource.Error(message))
        }
    }

    protected abstract suspend fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<NetworkResult<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}
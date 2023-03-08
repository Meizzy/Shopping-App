package com.brujiyaseer.shoppingapp.core.utils

import android.util.Log
import retrofit2.HttpException

private const val TAG = "SafeApiCall"

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Log.e(TAG, "error is HttpException ${throwable.message}")
                    Resource.Failure(throwable)
                }
                else -> {
                    Log.e(TAG, "error is ${throwable.message}")
                    Resource.Failure(throwable as Exception)
                }
            }
        }
    }
}
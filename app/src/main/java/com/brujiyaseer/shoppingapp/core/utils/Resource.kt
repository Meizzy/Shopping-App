package com.brujiyaseer.shoppingapp.core.utils

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
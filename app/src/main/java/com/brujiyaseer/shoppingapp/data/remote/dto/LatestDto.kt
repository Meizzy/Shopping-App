package com.brujiyaseer.shoppingapp.data.remote.dto

import com.brujiyaseer.shoppingapp.domain.model.Latest

data class LatestDto(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) {
    fun toLatest(): Latest {
        return Latest(
            category,
            image_url,
            name,
            price
        )
    }
}
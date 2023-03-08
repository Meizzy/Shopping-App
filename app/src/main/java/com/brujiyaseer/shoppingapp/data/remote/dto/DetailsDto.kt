package com.brujiyaseer.shoppingapp.data.remote.dto

import com.brujiyaseer.shoppingapp.domain.model.Details


data class DetailsDto(
    val colors: List<String>,
    val description: String,
    val image_urls: List<String>,
    val name: String,
    val number_of_reviews: Int,
    val price: Int,
    val rating: Double
) {
    fun toDetails(): Details {
        return Details(
            colors,
            description,
            image_urls,
            name,
            number_of_reviews,
            price,
            rating
        )
    }
}
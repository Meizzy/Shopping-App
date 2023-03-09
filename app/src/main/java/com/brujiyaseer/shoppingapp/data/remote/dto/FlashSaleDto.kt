package com.brujiyaseer.shoppingapp.data.remote.dto

import com.brujiyaseer.shoppingapp.domain.model.FlashSale

data class FlashSaleDto(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
) {
    fun toFlashSale(): FlashSale {
        return FlashSale(
            category, discount, image_url, name, price
        )
    }
}
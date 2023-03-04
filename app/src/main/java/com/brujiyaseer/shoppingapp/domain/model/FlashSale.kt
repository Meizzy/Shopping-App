package com.brujiyaseer.shoppingapp.domain.model

import com.brujiyaseer.shoppingapp.core.base.ListItem

data class FlashSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
) : ListItem {
    override val itemId: Long
        get() = name.hashCode().toLong()
}
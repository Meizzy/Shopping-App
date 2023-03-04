package com.brujiyaseer.shoppingapp.domain.model

import com.brujiyaseer.shoppingapp.core.base.ListItem

data class Latest(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) : ListItem {
    override val itemId: Long
        get() = name.hashCode().toLong()
}
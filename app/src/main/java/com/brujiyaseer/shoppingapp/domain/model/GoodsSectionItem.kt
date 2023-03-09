package com.brujiyaseer.shoppingapp.domain.model

import com.brujiyaseer.shoppingapp.core.base.ListItem

data class GoodsSectionItem(
    val title: String, val goods: List<ListItem>
) : ListItem {
    override val itemId: Long
        get() = title.hashCode().toLong()
}

package com.brujiyaseer.shoppingapp.data.remote.dto

import com.brujiyaseer.shoppingapp.domain.model.Search

data class SearchDto(
    val words: List<String>
) {
    fun toSearch(): Search {
        return Search(
            words
        )
    }
}

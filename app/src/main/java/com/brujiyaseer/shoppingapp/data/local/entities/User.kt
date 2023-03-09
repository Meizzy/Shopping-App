package com.brujiyaseer.shoppingapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    val firstName: String? = null, val lastName: String? = null, val email: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}
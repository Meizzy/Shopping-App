package com.brujiyaseer.shoppingapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brujiyaseer.shoppingapp.data.local.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsert(user: User): Long

    @Query("SELECT * FROM User WHERE uid = 1")
    fun getUser(): LiveData<User>

    @Query("DELETE FROM user WHERE uid = 1")
    suspend fun deleteUser()
}
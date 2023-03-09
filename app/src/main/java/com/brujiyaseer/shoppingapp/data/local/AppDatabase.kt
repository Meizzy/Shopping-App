package com.brujiyaseer.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brujiyaseer.shoppingapp.data.local.entities.User

const val DATABASE_NAME = "UserDatabase.db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [User::class], version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract val getUserDao: UserDao

}
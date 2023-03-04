package com.brujiyaseer.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brujiyaseer.shoppingapp.data.local.entities.User

const val DATABASE_NAME = "UserDatabase.db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [User::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract val getUserDao: UserDao

//    companion object{
//
//        @Volatile
//        private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance?:buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) =
//
//                Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    DATABASE_NAME
//                ).build()
//    }
}
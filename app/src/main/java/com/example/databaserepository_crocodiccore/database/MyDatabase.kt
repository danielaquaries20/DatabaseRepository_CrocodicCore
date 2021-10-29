package com.example.databaserepository_crocodiccore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databaserepository_crocodiccore.database.friend.Friend
import com.example.databaserepository_crocodiccore.database.friend.FriendDAO
import kotlinx.coroutines.internal.synchronized


@Database(
    entities = [Friend::class],
    version = 1,
    exportSchema = false
)

abstract class MyDatabase : RoomDatabase() {

    abstract fun daoFriend(): FriendDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
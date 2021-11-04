package com.example.databaserepository_crocodiccore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.databaserepository_crocodiccore.database.friend.Friend
import com.example.databaserepository_crocodiccore.database.friend.FriendDAO
import kotlinx.coroutines.internal.synchronized


@Database(
    entities = [Friend::class],
    version = 6,
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
                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        //Migration from 2 to 3 , Room 3..2..1..0
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Friend ADD COLUMN hobby TEXT NOT NULL DEFAULT ''")
            }
        }

//        val MIGRATION_4_5 = object : Migration(4, 5) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Friend RENAME COLUMN phoneNumber TO phone ")
//            }
//        }

    }
}
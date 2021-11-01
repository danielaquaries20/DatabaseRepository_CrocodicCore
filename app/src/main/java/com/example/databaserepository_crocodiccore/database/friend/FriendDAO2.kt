package com.example.databaserepository_crocodiccore.database.friend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.crocodic.core.data.CoreDao


@Dao
abstract class FriendDAO2 : CoreDao<Friend> {

    @Query("SELECT * FROM Friend")
    abstract fun getAll(): LiveData<List<Friend>>

}
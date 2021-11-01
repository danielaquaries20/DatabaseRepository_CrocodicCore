package com.example.databaserepository_crocodiccore.database.friend

import androidx.lifecycle.LiveData

class FriendRepository(private val friendDao: FriendDAO) {

    fun getAll(): LiveData<List<Friend>> {
        return friendDao.getAll()
    }
}
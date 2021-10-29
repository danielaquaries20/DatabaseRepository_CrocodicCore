package com.example.databaserepository_crocodiccore.database.friend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    var name: String,
    var gender: String,
    var school: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
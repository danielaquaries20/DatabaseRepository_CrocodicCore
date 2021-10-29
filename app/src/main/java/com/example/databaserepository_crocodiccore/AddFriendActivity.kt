package com.example.databaserepository_crocodiccore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databaserepository_crocodiccore.database.MyDatabase
import com.example.databaserepository_crocodiccore.database.friend.Friend
import com.example.databaserepository_crocodiccore.databinding.ActivityAddFriendBinding
import java.util.concurrent.Executors

class AddFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFriendBinding

    private lateinit var database: MyDatabase

    var name = ""
    var gender = ""
    var school = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend)
        binding.activityAdd = this

        database = MyDatabase.getDatabase(this)

        binding.btnSaveFriend.setOnClickListener {
            Log.d("tes data AddFriend", "$name $gender $school")
            if (name.isNotEmpty() && gender.isNotEmpty() && school.isNotEmpty()) {
                val friend = Friend(name, gender, school)

                Executors.newSingleThreadExecutor().execute {
                    database.daoFriend().insert(friend)
                }
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
                finish()
            }
        }

    }
}
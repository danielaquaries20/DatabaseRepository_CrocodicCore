package com.example.databaserepository_crocodiccore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databaserepository_crocodiccore.database.MyDatabase
import com.example.databaserepository_crocodiccore.database.friend.Friend
import com.example.databaserepository_crocodiccore.databinding.ActivityEditFriendBinding
import java.util.concurrent.Executors

class EditFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditFriendBinding

    private lateinit var database: MyDatabase

    var idFriend = 0
    var name = ""
    var gender = ""
    var school = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_friend)
        binding.activityEdit = this

        database = MyDatabase.getDatabase(this)

        idFriend = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name") ?: ""
        gender = intent.getStringExtra("gender") ?: ""
        school = intent.getStringExtra("school") ?: ""


        binding.btnSaveEdit.setOnClickListener {
            Log.d("tes data EditFriend", "$name $gender $school")
            if (name.isNotEmpty() && gender.isNotEmpty() && school.isNotEmpty()) {
                val friend = Friend(name, gender, school).apply {
                    id = idFriend
                }

                Executors.newSingleThreadExecutor().execute {
                    database.daoFriend().update(friend)
                }

                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
                finish()

            }
        }


        binding.btnDelete.setOnClickListener {
            val friend = Friend(name, gender, school).apply {
                id = idFriend
            }

            Executors.newSingleThreadExecutor().execute {
                database.daoFriend().delete(friend)
            }
            Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show()
            finish()

        }


    }
}
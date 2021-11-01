package com.example.databaserepository_crocodiccore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import com.example.databaserepository_crocodiccore.adapter.AdapterREAddFriend
import com.example.databaserepository_crocodiccore.database.MyDatabase
import com.example.databaserepository_crocodiccore.database.friend.FriendRepository
import com.example.databaserepository_crocodiccore.databinding.ActivityFriendListBinding

class FriendListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendListBinding

    private lateinit var database: MyDatabase

    private lateinit var repository: FriendRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_list)

        database = MyDatabase.getDatabase(this)
        repository = FriendRepository(database.daoFriend())

        repository.getAll().observe(this, {
            binding.adapter = AdapterREAddFriend(it) {
                val intent = Intent(this, EditFriendActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("name", it.name)
                    putExtra("gender", it.gender)
                    putExtra("school", it.school)
                }
                startActivity(intent)
            }
        })

//        database.daoFriend().getAll().observe(this, {
//            binding.adapter = AdapterREAddFriend(it) {
//                val intent = Intent(this, EditFriendActivity::class.java).apply {
//                    putExtra("id", it.id)
//                    putExtra("name", it.name)
//                    putExtra("gender", it.gender)
//                    putExtra("school", it.school)
//                }
//                startActivity(intent)
//            }
//        })

        binding.btnTambah.setOnClickListener {
            val maju = Intent(this, AddFriendActivity::class.java)
            startActivity(maju)
        }


    }
}

package com.example.databaserepository_crocodiccore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databaserepository_crocodiccore.R
import com.example.databaserepository_crocodiccore.database.friend.Friend
import com.example.databaserepository_crocodiccore.databinding.ItemRecyclerviewFriendListBinding

class AdapterREAddFriend(
    private val items: List<Friend>,
    private val onItemClick: (friend: Friend) -> Unit
) :
    RecyclerView.Adapter<AdapterREAddFriend.ViewHolderAddFriend>() {

    inner class ViewHolderAddFriend(val viewDataBinding: ItemRecyclerviewFriendListBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(friend: Friend?) {
            viewDataBinding.friend = friend
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAddFriend {
        return ViewHolderAddFriend(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_friend_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderAddFriend, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onItemClick(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
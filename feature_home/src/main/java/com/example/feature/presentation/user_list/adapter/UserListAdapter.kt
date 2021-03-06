package com.example.feature.presentation.user_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.utils.ConvertType
import com.example.core.utils.convertFromTimestampIntoDate
import com.example.feature.databinding.AdapterListItemBinding
import com.example.feature.domain.model.User

class UserListAdapter(
    private val onMoveToDetail: (User) -> Unit
) : ListAdapter<User, UserListAdapter.UserListViewHolder>(
    DiffCallback
) {

    class UserListViewHolder(
        private val binding: AdapterListItemBinding,
        val onMoveToDetail: (User) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                tvUserName.text = user.name
                tvUserTag.text = user.userTag
                tvDepartment.text = user.department
                tvBirthday.text = convertFromTimestampIntoDate(
                    user.timestamp,
                    ConvertType.DAY_OF_BIRTH
                )
                cvUserItem.setOnClickListener {
                    onMoveToDetail(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserListViewHolder(
            AdapterListItemBinding.inflate(layoutInflater, parent, false),
            onMoveToDetail
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

}
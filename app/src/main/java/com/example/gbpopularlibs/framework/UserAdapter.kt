package com.example.gbpopularlibs.framework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gbpopularlibs.R
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.databinding.GitHubUserItemBinding

class UserAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var list  = mutableListOf<GitHubUser>()
    fun setData(list : List<GitHubUser>) {
        this.list = list as MutableList<GitHubUser>
        notifyDataSetChanged()
    }
    inner class UserViewHolder(private val item : View) : RecyclerView.ViewHolder(item){
        private val binding = GitHubUserItemBinding.bind(item)
        fun bind(value : GitHubUser) = with(item){
            val avatar = value.avatarUrl
            val name = value.login

            avatar?.let {
                binding.avatarImageView.load(avatar){
                    error(R.drawable.ic_launcher_background)
                    placeholder(R.drawable.ic_launcher_background)
                }
            }
            binding.userNameTextView.text = name
            binding.containerGitHubUserItem.setOnClickListener {
                itemClickListener.itemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       return UserViewHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.git_hub_user_item,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.example.gbpopularlibs.framework

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var list  = mutableListOf<String>()
    fun setData(list : List<String>) {
        this.list.clear()
        this.list.addAll(list)
    }
    inner class UserViewHolder(private val item : TextView) : RecyclerView.ViewHolder(item){
        fun bind(value : String) = with(item){
            text = value
            setOnClickListener {
                itemClickListener.itemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = TextView(parent.context)
        view.textSize = 18.0F
        return UserViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.vinay.kotlin_coroutine_examples.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinay.kotlin_coroutine_examples.R
import com.vinay.kotlin_coroutine_examples.model.UserModel
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter(private val userList : ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserModel) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(userList[position])

    fun addData(list: List<UserModel>) {
        userList.addAll(list)
    }

}
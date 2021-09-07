package com.vinay.kotlin_coroutine_examples.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinay.kotlin_coroutine_examples.R
import com.vinay.kotlin_coroutine_examples.adapter.MoviePagingAdapter.*
import com.vinay.kotlin_coroutine_examples.databinding.RowMovieBinding
import com.vinay.kotlin_coroutine_examples.model.Movie
import com.vinay.kotlin_coroutine_examples.model.UserModel
import com.vinay.kotlin_coroutine_examples.network.RetrofitBuilder.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.row_movie.view.*

class MoviePagingAdapter : PagingDataAdapter<Movie, MyViewHolder>(DIFF_UTIL) {


    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.equals(newItem)
//                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MoviePagingAdapter.MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
//            holder.bind(currentItem)
//
//            holder.itemView.image_view

            Glide.with(holder.itemView.image_view.context)
                .load(IMAGE_BASE_URL+currentItem.posterPath)
                .into(holder.itemView.image_view)

            holder.itemView.tv_title.setText(currentItem.title)
            holder.itemView.tv_description.setText(currentItem.overview)



        }
    }
//        holder.bind(userList[position])


    class MyViewHolder(itemView: RowMovieBinding) : RecyclerView.ViewHolder(itemView.root)

}
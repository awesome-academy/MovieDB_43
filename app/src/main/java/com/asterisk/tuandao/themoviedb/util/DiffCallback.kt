package com.asterisk.tuandao.themoviedb.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.asterisk.tuandao.themoviedb.data.source.model.Movie

object DiffCallback {
    val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}

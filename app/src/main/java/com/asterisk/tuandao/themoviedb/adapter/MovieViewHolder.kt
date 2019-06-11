package com.asterisk.tuandao.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemHomeMovieBinding
import com.asterisk.tuandao.themoviedb.ui.home.HomeViewModel

class MovieViewHolder(
        val binding: ItemHomeMovieBinding,
        homeViewModel: HomeViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewmodel = homeViewModel
    }

    fun bindView(data: Movie?) {
        binding.run {
            movie = data
        }
    }

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup, viewModel: HomeViewModel): MovieViewHolder {
            return MovieViewHolder(DataBindingUtil.inflate(layoutInflater,
                    R.layout.item_home_movie, parent, false), viewModel)
        }
    }
}

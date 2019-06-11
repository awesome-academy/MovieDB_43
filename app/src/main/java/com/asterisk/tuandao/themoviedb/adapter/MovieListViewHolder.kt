package com.asterisk.tuandao.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R

import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemListMovieBinding
import com.asterisk.tuandao.themoviedb.ui.search.SearchViewModel

class MovieListViewHolder(
        val binding: ItemListMovieBinding,
        searchViewModel: SearchViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewModel = searchViewModel
    }

    fun bindView(data: Movie?) {
        binding.run {
            this.data = data
        }
    }

    companion object {
        fun create(layoutInflater: LayoutInflater,
                   parent: ViewGroup, searchViewModel: SearchViewModel): MovieListViewHolder {
            return MovieListViewHolder(DataBindingUtil.inflate(layoutInflater,
                    R.layout.item_list_movie, parent, false), searchViewModel)
        }
    }
}

package com.asterisk.tuandao.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemGenreMovieBinding
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieViewModel

class GenreMovieViewHolder(
        val binding: ItemGenreMovieBinding,
        genreMovieViewModel: GenreMovieViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewmodel = genreMovieViewModel
    }

    fun bindView(data: Movie?) {
        binding.run {
            movie = data
        }
    }

    companion object {
        fun create(layoutInflater: LayoutInflater,
                   parent: ViewGroup, genreMovieViewModel: GenreMovieViewModel): GenreMovieViewHolder {
            return GenreMovieViewHolder(DataBindingUtil.inflate(layoutInflater,
                    R.layout.item_genre_movie, parent, false), genreMovieViewModel)
        }
    }
}

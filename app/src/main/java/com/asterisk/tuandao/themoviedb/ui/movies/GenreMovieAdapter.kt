package com.asterisk.tuandao.themoviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemGenreMovieBinding
import com.asterisk.tuandao.themoviedb.util.DiffCallback

class GenreMovieAdapter(private var movies: List<Movie>, val genreMovieViewModel: GenreMovieViewModel) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallback.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GenreMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_genre_movie, parent, false
            ), genreMovieViewModel
        )
    }

    override fun getItemCount() = super.getItemCount()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as GenreMovieViewHolder).bindView(movie)
    }

    class GenreMovieViewHolder(
        val binding: ItemGenreMovieBinding,
        genreMovieViewModel: GenreMovieViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = genreMovieViewModel
        }

        fun bindView(data: Movie?) {
            binding.run {
                this.data = data
            }
        }
    }
}

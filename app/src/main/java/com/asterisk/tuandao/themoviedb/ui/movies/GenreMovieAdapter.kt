package com.asterisk.tuandao.themoviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemGenreMovieBinding

class GenreMovieAdapter(
    private var movies: List<Movie>,
    val genreMovieViewModel: GenreMovieViewModel
) : RecyclerView.Adapter<GenreMovieAdapter.GenreMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreMovieViewHolder {
        return GenreMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_genre_movie, parent, false
            ), genreMovieViewModel
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: GenreMovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)
    }

    fun swapAdapter(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    class GenreMovieViewHolder(
        val binding: ItemGenreMovieBinding
        , genreMovieViewModel: GenreMovieViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = genreMovieViewModel
        }

        fun bindView(data: Movie?) {
            binding.run {
                movie = data
            }
        }
    }
}

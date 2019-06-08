package com.asterisk.tuandao.themoviedb.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemListFavoriteBinding
import com.asterisk.tuandao.themoviedb.databinding.ItemListMovieBinding

class FavoriteAdapter(
    var movies: List<Movie>, val favoriteViewModel: FavoriteViewModel
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_movie, parent, false
            ), favoriteViewModel
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)
    }

    fun swapAdapter(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    fun clearAll(){
        (movies as MutableList).clear()
    }

    class FavoriteViewHolder(
        val binding: ItemListFavoriteBinding,
        favoriteViewModel: FavoriteViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = favoriteViewModel
        }

        fun bindView(movie: Movie?) {
            binding.run {
                data = movie
            }
        }
    }
}

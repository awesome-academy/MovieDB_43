package com.asterisk.tuandao.themoviedb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemListMovieBinding

class SearchMovieAdapter(
    var movies: List<Movie>, val searchViewModel: SearchViewModel
) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_movie, parent, false
            ), searchViewModel
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
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

    class SearchMovieViewHolder(
        val binding: ItemListMovieBinding,
        searchViewModel: SearchViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = searchViewModel
        }

        fun bindView(movie: Movie?) {
            binding.run {
                data = movie
            }
        }
    }
}

package com.asterisk.tuandao.themoviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemHomeMovieBinding

class HomeAdapter(
    private var movies: List<Movie>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_movie, parent, false
            ), viewModel
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie, viewModel)
    }

    fun swapAdapter(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    class HomeViewHolder(
        val binding: ItemHomeMovieBinding,
        homeViewModel: HomeViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = homeViewModel
        }

        fun bindView(data: Movie?, homeViewModel: HomeViewModel) {
            binding.run {
                movie = data
                viewmodel = homeViewModel
            }
        }
    }
}

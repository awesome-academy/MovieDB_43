package com.asterisk.tuandao.themoviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemHomeMovieBinding
import com.asterisk.tuandao.themoviedb.ui.main.MainViewModel

class HomeAdapter(private var movies: List<Movie>, private val viewModel: MainViewModel) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_movie, parent, false
            )
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

    class HomeViewHolder(val binding: ItemHomeMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: Movie?, mainViewModel: MainViewModel) {
            binding.run {
                movie = data
//              viewmodel = mainViewModel
            }
        }
    }
}

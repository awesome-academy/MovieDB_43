package com.asterisk.tuandao.themoviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemHomeMovieBinding
import com.asterisk.tuandao.themoviedb.util.DiffCallback.diffCallback

class HomeAdapter(private var movies: List<Movie>, private val viewModel: HomeViewModel, private val retryCallback: () -> Unit) :
        PagedListAdapter<Movie, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(DataBindingUtil.inflate(layoutInflater,
                R.layout.item_home_movie, parent, false), viewModel)
    }

    override fun getItemCount() = super.getItemCount()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as HomeViewHolder).bindView(movie)
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

        fun bindView(data: Movie?) {
            binding.run {
                movie = data
            }
        }
    }

}

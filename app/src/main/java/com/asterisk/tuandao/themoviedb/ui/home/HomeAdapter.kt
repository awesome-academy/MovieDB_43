package com.asterisk.tuandao.themoviedb.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemHomeMovieBinding

class HomeAdapter(context: Context,private var movies: List<Movie>, private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun getItemCount() = movies.size

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                inflater,
               R.layout.item_home_movie, parent, false
            ), viewModel
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)
    }

    fun swapAdapter(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    fun addData(newMovies: List<Movie>) {
        val startPosition = movies.size
        (movies as MutableList).addAll(newMovies)
        notifyItemRangeInserted(startPosition, movies.size)
    }

    fun replaceData(_movies: List<Movie>) {
        Log.d("HomeAdapter", "${movies.size}")
        (movies as MutableList).clear()
        (movies as MutableList).addAll(_movies)
        Log.d("HomeAdapter", "${movies.size}")
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
                executePendingBindings()
            }
        }

    }

//    override fun submitList(list: MutableList<Movie>?) {
//        super.submitList(if (list != null) ArrayList(list) else null)
//    }
//
//    class UserDiffCallBack : DiffUtil.ItemCallback<Movie>() {
//        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    fun onNewData() {
//        val diffResult = DiffUtil.calculateDiff()
//    }
}

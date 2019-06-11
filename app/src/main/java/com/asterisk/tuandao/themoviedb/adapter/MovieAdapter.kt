package com.asterisk.tuandao.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.NetworkState
import com.asterisk.tuandao.themoviedb.ui.home.HomeViewModel
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieViewModel
import com.asterisk.tuandao.themoviedb.ui.search.SearchViewModel
import com.asterisk.tuandao.themoviedb.util.DiffCallback

class MovieAdapter(private val context: Context,
                   private val layoutId: Int,
                   private val viewModel: ViewModel,
                   private val retryCallback: () -> Unit)
    : PagedListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallback.diffCallback) {
    private var networkState: NetworkState? = null
    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_home_movie -> MovieViewHolder.create(layoutInflater, parent,
                    viewModel as HomeViewModel)
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(layoutInflater,
                    parent, retryCallback)
            R.layout.item_list_movie -> MovieListViewHolder.create(layoutInflater, parent,
                    viewModel as SearchViewModel)
            R.layout.item_genre_movie -> GenreMovieViewHolder.create(layoutInflater, parent,
                    viewModel as GenreMovieViewModel)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_home_movie -> {
                val movie = getItem(position)
                (holder as MovieViewHolder).bindView(movie)
            }
            R.layout.item_list_movie -> {
                val movie = getItem(position)
                (holder as MovieListViewHolder).bindView(movie)
            }
            R.layout.item_genre_movie -> {
                val movie = getItem(position)
                (holder as GenreMovieViewHolder).bindView(movie)
            }
            R.layout.network_state_item -> {
                (holder as NetworkStateItemViewHolder).bindView(networkState)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            when (layoutId) {
                R.layout.item_home_movie -> R.layout.item_home_movie
                R.layout.item_list_movie -> R.layout.item_list_movie
                else -> R.layout.item_genre_movie
            }
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}

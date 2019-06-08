package com.asterisk.tuandao.themoviedb.ui.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Actor
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.databinding.ItemActorRelatedMovieBinding

class RelativeMovieAdapter(
    private var movies: List<Movie>,
    private val actorViewModel: ActorViewModel
) : RecyclerView.Adapter<RelativeMovieAdapter.RelativeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelativeViewHolder {
        return RelativeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_actor_related_movie, parent, false), actorViewModel)
    }

    override fun getItemCount() = movies.size

    fun swapAdapter(newCasts: List<Movie>) {
        movies = newCasts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RelativeViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)
    }

    class RelativeViewHolder(
        val binding: ItemActorRelatedMovieBinding,
        actorViewModel: ActorViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = actorViewModel
        }

        fun bindView(data: Movie?) {
            binding.movie = data
        }
    }
}

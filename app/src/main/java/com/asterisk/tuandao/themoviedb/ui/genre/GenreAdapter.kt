package com.asterisk.tuandao.themoviedb.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.databinding.ItemGenreMovieBinding

class GenreAdapter(private var genres: List<Genre>, private val genreViewModel: GenreViewModel) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_genre_movie, parent, false
            ), genreViewModel
        )
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bindView(genre, genreViewModel)
    }

    fun swapAdapter(newGenres: List<Genre>) {
        genres = newGenres
        notifyDataSetChanged()
    }

    class GenreViewHolder(val binding: ItemGenreMovieBinding, genreViewModel: GenreViewModel) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = genreViewModel
        }
        fun bindView(data: Genre?, genreViewModel: GenreViewModel) {
            binding.run {
                this.genre = data
            }
        }
    }
}

package com.asterisk.tuandao.themoviedb.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.databinding.ItemGenreBinding

class GenreAdapter(private var genres: List<Genre>, private val genreViewModel: GenreViewModel) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_genre, parent, false
            ), genreViewModel
        )
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bindView(genre)
    }

    fun swapAdapter(newGenres: List<Genre>) {
        genres = newGenres
        notifyDataSetChanged()
    }

    class GenreViewHolder(
        val binding: ItemGenreBinding,
        genreViewModel: GenreViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.genreViewModel = genreViewModel
        }

        fun bindView(data: Genre?) {
            binding.run {
                this.genre = data
            }
        }
    }
}

package com.asterisk.tuandao.themoviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Actor
import com.asterisk.tuandao.themoviedb.databinding.ItemActorBinding

class CastAdapter(private var casts: List<Actor>, private val detailViewModel: DetailViewModel)
    : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.item_actor, parent, false), detailViewModel)
    }

    override fun getItemCount() = casts.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = casts[position]
        holder.bindView(cast)
    }

    fun swapCast(newCasts: List<Actor>) {
        casts = newCasts
        notifyDataSetChanged()
    }

    class CastViewHolder(val binding: ItemActorBinding, detailViewModel: DetailViewModel)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = detailViewModel
        }

        fun bindView(data: Actor?) {
           binding.actor = data
        }
    }
}

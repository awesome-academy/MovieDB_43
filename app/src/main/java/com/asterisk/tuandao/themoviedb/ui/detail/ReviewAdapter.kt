package com.asterisk.tuandao.themoviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Review
import com.asterisk.tuandao.themoviedb.databinding.ItemReviewBinding

class ReviewAdapter(private var reviews: List<Review>, private val detailViewModel: DetailViewModel)
    : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.item_review, parent, false), detailViewModel)
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bindView(review)
    }

    fun swapReview(newReviews: List<Review>) {
        reviews = newReviews
        notifyDataSetChanged()
    }

    class ReviewViewHolder(val binding: ItemReviewBinding, detailViewModel: DetailViewModel)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewmodel = detailViewModel
        }

        fun bindView(data: Review?) {
            binding.review = data
        }
    }
}

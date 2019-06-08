package com.asterisk.tuandao.themoviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.databinding.FragmentFavoriteBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), FavoriteNavigator{
    override val layoutId: Int
        get() = R.layout.fragment_favorite
    private lateinit var viewDataBinding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun initComponents() {
        initAdapter()
        doObserve()
        doObserveClickMovie()
    }

    fun doObserveClickMovie() {
        favoriteViewModel.movieDetail.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                openMovieDetails(it)
            }
        })
    }

    override fun openMovieDetails(movieId: Int) {
        DetailActivity.getIntent(activity as Context, movieId).apply {
            startActivity(this)
        }
    }

    private fun initAdapter() {
        favoriteAdapter = FavoriteAdapter(ArrayList(), favoriteViewModel)
        with(viewDataBinding) {
            recyclerFavorite.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerFavorite.hasFixedSize()
            recyclerFavorite.adapter = favoriteAdapter
        }
    }

    private fun doObserve() {
        favoriteViewModel.favorite.observe(this, Observer {
            it?.let { movies ->
                favoriteAdapter.swapAdapter(movies)
            }
        })
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}

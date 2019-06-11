package com.asterisk.tuandao.themoviedb.ui.movies

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.adapter.MovieAdapter
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.NetworkState
import com.asterisk.tuandao.themoviedb.data.source.remote.Status
import com.asterisk.tuandao.themoviedb.databinding.ActivityGenreMovieBinding
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class GenreMovieActivity : DaggerAppCompatActivity(), GenreMovieNavigator {
    private lateinit var genreMovieAdapter: MovieAdapter
    private lateinit var viewDataBinding: ActivityGenreMovieBinding
    var itemDecoration: RecyclerView.ItemDecoration? = null
    @Inject
    lateinit var genreMovieViewModel: GenreMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDefaultDisplayHomeAsUpEnabled(true)
        }
        intent.getStringExtra(Constants.GENRE_ID_TAG)?.let {
            genreMovieViewModel.setSelectedGenre(it)
        }
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_genre_movie)
        initAdapter()
        initComponent()
    }

    override fun openMovieDetails(movieId: Int) {
        DetailActivity.getIntent(this, movieId).apply {
            startActivity(this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        genreMovieAdapter = MovieAdapter(this, R.layout.item_genre_movie,
                genreMovieViewModel) { genreMovieViewModel.retry() }
        with(viewDataBinding) {
            recyclerMovie.layoutManager = GridLayoutManager(
                    this@GenreMovieActivity,
                    Constants.SPAN_COUNT
            )
            recyclerMovie.addItemDecoration(DividerItemDecoration(this@GenreMovieActivity, 0))
            recyclerMovie.adapter = genreMovieAdapter
        }
    }

    private fun initComponent() {
        doObserve()
    }

    private fun doObserve() {
        genreMovieViewModel.openMovieEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let { movieId ->
                openMovieDetails(movieId)
            }
        })
        genreMovieViewModel.movies.observe(this, Observer {
            showSuccess(it)
        })
        genreMovieViewModel.networkState.observe(this, Observer {
            genreMovieAdapter.setNetworkState(it)
        })
        genreMovieViewModel.refreshState.observe(this, Observer {
            viewDataBinding.refresh.isRefreshing = it == NetworkState.LOADING
        })
        viewDataBinding.refresh.setOnRefreshListener {
            genreMovieViewModel.refresh()
        }
    }

    private fun showSuccess(data: PagedList<Movie>?) {
        data?.let {
            genreMovieAdapter.submitList(it)
            while (viewDataBinding.recyclerMovie.itemDecorationCount > 0
                    && (viewDataBinding.recyclerMovie.getItemDecorationAt(
                            0
                    )?.let { itemDecoration = it }) != null
            ) {
                viewDataBinding.recyclerMovie.removeItemDecoration(itemDecoration!!)
            }
        }
    }

    private fun showError(message: String?) {
        message?.let {
            this?.showMessage(it)
        }
    }
}

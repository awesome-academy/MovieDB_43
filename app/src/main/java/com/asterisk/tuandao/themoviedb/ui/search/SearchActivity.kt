package com.asterisk.tuandao.themoviedb.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.adapter.MovieAdapter
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.NetworkState
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.remote.Status
import com.asterisk.tuandao.themoviedb.databinding.ActivitySearchBinding
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity(), SearchView.OnQueryTextListener, SearchNavigator {
    private lateinit var viewDataBinding: ActivitySearchBinding
    private lateinit var searchAdapter: MovieAdapter
    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = searchViewModel
        initComponent()
        viewDataBinding.shimmerViewContainer.visibility = View.GONE
        viewDataBinding.recyclerSearchMovies.visibility = View.VISIBLE
    }

    private fun initComponent() {
        setupActionBar()
        initAdapter()
        initBackBtn()
        doObserve()
        doObserveClickMovie()
        viewDataBinding.textSearch.setOnQueryTextListener(this)
    }

    fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
    }

    fun initAdapter() {
        searchAdapter = MovieAdapter(this, R.layout.item_list_movie, searchViewModel){
            searchViewModel.retry()
        }
        with(viewDataBinding) {
            recyclerSearchMovies.layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.VERTICAL, false)
            recyclerSearchMovies.adapter = searchAdapter
        }
    }

    private fun initBackBtn() {
        viewDataBinding.buttonSearchBack.setOnClickListener {
            val editText = viewDataBinding.textSearch
            if (editText.hasFocus()) {
                editText.clearFocus()
                return@setOnClickListener
            }
            onBackPressed()
        }
    }

    private fun doObserve() {
        searchViewModel.movies.observe(this, Observer {
            showSuccess(it)
        })

        searchViewModel.networkState.observe(this, Observer {
            searchAdapter.setNetworkState(it)
            when(it.status) {
                Status.RUNNING -> {

                }
            }
        })
        searchViewModel.refreshState.observe(this, Observer {
            viewDataBinding.refresh.isRefreshing = it == NetworkState.LOADING
        })
        viewDataBinding.refresh.setOnRefreshListener {
            searchViewModel.refresh()
        }
    }

    private fun doObserveClickMovie() {
        searchViewModel.movieDetail.observe(this, Observer {
            it.getContentIfNotHandled()?.let { movieId ->
                openMovieDetail(movieId)
            }
        })
    }

    private fun showSuccess(data: PagedList<Movie>?) {
        Handler().postDelayed({
            data?.let {
                searchAdapter.submitList(it)
                viewDataBinding.shimmerViewContainer.stopShimmer()
                viewDataBinding.shimmerViewContainer.visibility = View.GONE
                viewDataBinding.recyclerSearchMovies.visibility = View.VISIBLE
            }
        },2000)
    }

    private fun showError(message: String?) {
        message?.let {
            showMessage(it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!!.isEmpty()) return false
        searchAdapter.submitList(null)
        searchViewModel.setKeySearch(query)
        viewDataBinding.shimmerViewContainer.startShimmer()
        viewDataBinding.shimmerViewContainer.visibility = View.VISIBLE
        viewDataBinding.recyclerSearchMovies.visibility = View.GONE
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query!!.isEmpty()) return false
        searchAdapter.submitList(null)
        searchViewModel.setKeySearch(query)
        viewDataBinding.shimmerViewContainer.startShimmer()
        viewDataBinding.shimmerViewContainer.visibility = View.VISIBLE
        viewDataBinding.recyclerSearchMovies.visibility = View.GONE
        return true
    }

    override fun openMovieDetail(movieId: Int) {
        DetailActivity.getIntent(this, movieId).apply {
            startActivity(this)
        }
    }

    override fun onPause() {
        super.onPause()
        viewDataBinding.shimmerViewContainer.stopShimmer()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }
}

package com.asterisk.tuandao.themoviedb.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.ActivitySearchBinding
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity(), SearchView.OnQueryTextListener, SearchNavigator {
    private lateinit var viewDataBinding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchMovieAdapter
    @Inject
    lateinit var searchViewModel: SearchViewModel
    private var page = 1
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = searchViewModel
        initComponent()
    }

    fun initComponent() {
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
        searchAdapter = SearchMovieAdapter(ArrayList(), searchViewModel)
        with(viewDataBinding) {
            recyclerSearchMovies.layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.VERTICAL, false)
            recyclerSearchMovies.hasFixedSize()
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
            when (it) {
                is Resources.Progress -> {
                    //do something
                }
                is Resources.Success -> {
                    showSuccess(it.data?.results)
                }
                is Resources.Failure -> {
                    showError(it.e.message)
                }
            }
        })
    }

    private fun doObserveClickMovie() {
        searchViewModel.movieDetail.observe(this, Observer {
            it.getContentIfNotHandled()?.let { movieId ->
                openMovieDetail(movieId)
            }
        })
    }

    private fun showSuccess(data: List<Movie>?) {
        data?.let {
            searchAdapter.swapAdapter(it)
        }
    }

    private fun showError(message: String?) {
        message?.let {
            showMessage(it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!!.isEmpty()) return false
        this.query = query
        searchAdapter.clearAll()
        searchViewModel.searchMovies(query, page)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query!!.isEmpty()) return false
        this.query = query
        searchAdapter.clearAll()
        searchViewModel.searchMovies(query, page)
        return true
    }

    override fun openMovieDetail(movieId: Int) {
        DetailActivity.getIntent(this, movieId).apply {
            startActivity(this)
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }
}

package com.asterisk.tuandao.themoviedb.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.asterisk.tuandao.themoviedb.databinding.FragmentHomeBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.ui.search.SearchActivity
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.showMessage
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMovieNavigator {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewDataBinding: FragmentHomeBinding
    var itemDecoration: RecyclerView.ItemDecoration? = null
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = homeViewModel
            lifecycleOwner = this@HomeFragment
        }
        initAdapter()
        return viewDataBinding.root
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter(context!!, R.layout.item_home_movie, homeViewModel){
            homeViewModel.retry()
        }
        with(viewDataBinding) {
            viewDataBinding.recyclerMovie.layoutManager = GridLayoutManager(activity, 2)
            recyclerMovie.addItemDecoration(DividerItemDecoration(activity, 0))
            recyclerMovie.adapter = movieAdapter
        }
    }

    override fun initComponents() {
        doObserveClickedMovie()
        doObserveClickedSearch()
        doObserve()
    }

    override fun openMovieDetails(movieId: Int) {
        DetailActivity.getIntent(activity as Context, movieId).apply {
            startActivity(this)
        }
    }

    override fun openSearchMovie() {
        SearchActivity.getIntent(activity as Context).apply {
            startActivity(this)
        }
    }

    private fun doObserve() {
        homeViewModel.movies.observe(this, Observer {
            showSuccess(it)
        })
        homeViewModel.networkState.observe(this, Observer {
//            movieAdapter.setNetworkState(it)
            when(it.status) {
                Status.FAILED -> showError(it.msg)
            }
        })
        homeViewModel.refreshState.observe(this, Observer {
            viewDataBinding.refresh.isRefreshing = it == NetworkState.LOADING
        })
        viewDataBinding.refresh.setOnRefreshListener {
            homeViewModel.refresh()
        }
    }

    private fun showSuccess(data: PagedList<Movie>?) {
        data?.let {
            movieAdapter.submitList(it)
            removeLineReyclerView()
        }
    }

    private fun removeLineReyclerView() {
        while (viewDataBinding.recyclerMovie.itemDecorationCount > 0
                && (viewDataBinding.recyclerMovie.getItemDecorationAt(
                        0
                )?.let { itemDecoration = it }) != null
        ) {
            viewDataBinding.recyclerMovie.removeItemDecoration(itemDecoration!!)
        }
    }

    private fun showError(message: String?) {
        message?.let {
            activity?.showMessage(it)
        }
    }

    private fun doObserveClickedMovie() {
        homeViewModel.openMovieEvent.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                openMovieDetails(it)
            }
        })
    }

    private fun doObserveClickedSearch() {
        homeViewModel.openSearchEvent.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                openSearchMovie()
            }
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

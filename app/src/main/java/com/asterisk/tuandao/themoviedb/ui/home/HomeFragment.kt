package com.asterisk.tuandao.themoviedb.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.FragmentHomeBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.ui.search.SearchActivity
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.showMessage
import java.util.concurrent.locks.Lock
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMovieNavigator {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private lateinit var homeAdapter: HomeAdapter
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
        homeAdapter = HomeAdapter(this.context!!,ArrayList(), homeViewModel)
        with(viewDataBinding) {
            recyclerMovie.layoutManager = GridLayoutManager(activity, Constants.SPAN_COUNT)
//            recyclerMovie.setHasFixedSize(true)
            recyclerMovie.addItemDecoration(DividerItemDecoration(activity, 0))
            recyclerMovie.adapter = homeAdapter
        }
    }

    override fun initComponents() {
        doObserveClickedMovie()
        doObserveClickedSearch()
        doObserve()
        loadMore()
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
        homeViewModel.movie?.observe(this as Fragment, Observer {
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

    private fun showSuccess(data: List<Movie>?) {
        data?.let {
            Log.d("showSuccess","${data.size}")
            homeAdapter.addData(data)
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

    private fun loadMore() {
        viewDataBinding.nestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    homeViewModel.setLoadMore(true)
                    homeViewModel.increareCurrentPage()
                    homeViewModel.getMoreMovies()
                }
            })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

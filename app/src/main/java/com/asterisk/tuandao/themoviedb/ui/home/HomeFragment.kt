package com.asterisk.tuandao.themoviedb.ui.home

import android.content.Context
import android.graphics.pdf.PdfDocument
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
        homeAdapter = HomeAdapter(ArrayList(), homeViewModel)
        with(viewDataBinding) {
            recyclerMovie.layoutManager = GridLayoutManager(activity, Constants.SPAN_COUNT)
            recyclerMovie.setHasFixedSize(true)
            recyclerMovie.addItemDecoration(DividerItemDecoration(activity, 0))
            recyclerMovie.adapter = homeAdapter
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
//        homeViewModel.getMoviePagedList().observe(this, Observer {
//            homeAdapter.submitList(it)
//            removeLineReyclerView()
//        })
//
//        homeViewModel.getLoading().observe(this, Observer {
//            when (it) {
//                is Resources.Progress -> {
//                    viewDataBinding.processBar.visibility = View.VISIBLE
//                }
//                is Resources.Success -> {
//                    viewDataBinding.processBar.visibility = View.GONE
//                }
//                is Resources.Failure -> {
//                    viewDataBinding.processBar.visibility = View.GONE
//                    showError(it.e.message)
//                }
//            }
//        })
        homeViewModel.movies.observe(this, Observer {
            showSuccess(it)
        })
        homeViewModel.networkState.observe(this, Observer {
            when(it?.status) {
                Status.RUNNING -> {
                    viewDataBinding.processBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    viewDataBinding.processBar.visibility = View.GONE
                    viewDataBinding.retryButton.visibility = View.GONE
                }
                Status.FAILED -> {
                    viewDataBinding.processBar.visibility = View.GONE
                    viewDataBinding.retryButton.visibility = View.VISIBLE
                }
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
            homeAdapter.submitList(it)
            while (viewDataBinding.recyclerMovie.itemDecorationCount > 0
                && (viewDataBinding.recyclerMovie.getItemDecorationAt(
                    0
                )?.let { itemDecoration = it }) != null
            ) {
                viewDataBinding.recyclerMovie.removeItemDecoration(itemDecoration!!)
            }
        }
    }

    fun removeLineReyclerView() {
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

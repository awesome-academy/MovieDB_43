package com.asterisk.tuandao.themoviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.FragmentHomeBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import com.asterisk.tuandao.themoviedb.util.showMessage
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewDataBinding: FragmentHomeBinding
    var itemDecoration: RecyclerView.ItemDecoration? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this as Fragment, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getMovies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = homeViewModel
        }
        initAdapter()
        return viewDataBinding.root
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter(ArrayList(), homeViewModel)
        viewDataBinding.recyclerMovie.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
        viewDataBinding.recyclerMovie.addItemDecoration(DividerItemDecoration(activity, 0))
        viewDataBinding.recyclerMovie.adapter = homeAdapter
    }

    override fun initComponents() {
        doObserve()
    }

    private fun doObserve() {
        homeViewModel.movie.observe(this as Fragment, Observer {
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
            homeAdapter.swapAdapter(it)
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

    companion object {
        fun newInstance() = HomeFragment()
        const val SPAN_COUNT = 2
    }
}

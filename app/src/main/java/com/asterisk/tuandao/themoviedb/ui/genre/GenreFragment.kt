package com.asterisk.tuandao.themoviedb.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.FragmentGenreBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.util.showMessage
import javax.inject.Inject

class GenreFragment : BaseFragment(), GenreItemNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_genre
    private lateinit var viewDataBinding: FragmentGenreBinding
    private lateinit var genreAdapter: GenreAdapter
    @Inject
    lateinit var genreViewModel: GenreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentGenreBinding.inflate(inflater, container, false)
        initAdapter()
        return viewDataBinding.root
    }

    override fun openGenreMovies(genreId: String) {
        //open genre movies
    }

    private fun initAdapter() {
        genreAdapter = GenreAdapter(ArrayList(), genreViewModel)
        with(viewDataBinding) {
            recyclerGenre.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerGenre.setHasFixedSize(true)
            recyclerGenre.adapter = genreAdapter
        }
    }

    override fun initComponents() {
        //do observer
        doObserve()
        doObserveClickedGenre()
    }

    private fun showSuccess(genres: List<Genre>?) {
        genres?.let {
            genreAdapter.swapAdapter(it)
        }
    }

    private fun showError(message: String?) {
        message?.let {
            activity?.showMessage(it)
        }
    }

    private fun doObserve() {
        genreViewModel.genres.observe(this, Observer {
            when (it) {
                is Resources.Progress -> {
                    //do something
                }
                is Resources.Success -> {
                    showSuccess(it.data?.genres)
                }
                is Resources.Failure -> {
                    showError(it.e.message)
                }
            }
        })
    }

    private fun doObserveClickedGenre() {
        genreViewModel.selectedGenre.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                openGenreMovies(it)
            }
        })
    }

    companion object {
        fun newInstance() = GenreFragment()
    }
}

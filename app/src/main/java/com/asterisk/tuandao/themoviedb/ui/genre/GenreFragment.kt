package com.asterisk.tuandao.themoviedb.ui.genre

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.FragmentGenreBinding
import com.asterisk.tuandao.themoviedb.ui.base.BaseFragment
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieActivity
import com.asterisk.tuandao.themoviedb.util.showMessage
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

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

    override fun openGenreMovies(genreId: String, title: String) {
        //open genre movies
        GenreMovieActivity.getIntent(this.context!!, genreId, title).apply {
            startActivity(this)
        }
    }

    private fun initAdapter() {
        genreAdapter = GenreAdapter(ArrayList(), genreViewModel)
        with(viewDataBinding) {
            recyclerGenre.layoutManager = GridLayoutManager(activity, 2)
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
        Handler().postDelayed({
            genres?.let {
                var nowPlaying = Genre()
                nowPlaying.id = "1"
                nowPlaying.name = "Now Playing"
                var topRate = Genre()
                topRate.id = "2"
                topRate.name = "Top Rate"
                var upComing = Genre()
                upComing.id = "3"
                upComing.name = "Up Coming"
                Collections.reverse(it)
                (it as ArrayList).add(nowPlaying)
                it.add(topRate)
                it.add(upComing)
                Collections.reverse(it)
                genreAdapter.swapAdapter(it)
                shimmerViewContainer.stopShimmer()
                shimmerViewContainer.visibility = View.GONE
                viewDataBinding.recyclerGenre.visibility = View.VISIBLE
            }
        }, 3000)
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
                    shimmerViewContainer.startShimmer()
                    shimmerViewContainer.visibility = View.VISIBLE
                    viewDataBinding.recyclerGenre.visibility = View.GONE
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
        genreViewModel.selectedGenre.observe(this, Observer {
            openGenreMovies(it[0], it[1])
        })
    }

    override fun onPause() {
        super.onPause()
        shimmerViewContainer.stopShimmer()
    }

    companion object {
        fun newInstance() = GenreFragment()
    }
}

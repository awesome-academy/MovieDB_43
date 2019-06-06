package com.asterisk.tuandao.themoviedb.ui.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.ActivityGenreMovieBinding
import com.asterisk.tuandao.themoviedb.ui.genre.GenreFragment
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class GenreMovieActivity : DaggerAppCompatActivity(), GenreMovieNavigator {
    private lateinit var genreMovieAdapter: GenreMovieAdapter
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
        intent.getStringExtra(GENRE_ID_TAG)?.let {
            genreMovieViewModel.setSelectedGenre(it)
        }
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_genre_movie)
        initAdapter()
        initComponent()
    }

    override fun openMovieDetails(movieId: Int) {
        //open detail movie
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        genreMovieAdapter = GenreMovieAdapter(ArrayList(), genreMovieViewModel)
        with(viewDataBinding) {
            recyclerMovie.layoutManager = GridLayoutManager(this@GenreMovieActivity, Constants.SPAN_COUNT)
            recyclerMovie.setHasFixedSize(true)
            recyclerMovie.addItemDecoration(
                    DividerItemDecoration(this@GenreMovieActivity, 0))
            recyclerMovie.adapter = genreMovieAdapter
        }
    }

    private fun initComponent() {
        doObserve()
    }

    private fun doObserve() {
        genreMovieViewModel.selectedGenre.observe(this, Observer {
            genreMovieViewModel.getMoviesByGenre(it)
        })
        genreMovieViewModel.movie?.observe(this, Observer {
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
            genreMovieAdapter.swapAdapter(it)
            while (viewDataBinding.recyclerMovie.itemDecorationCount > 0
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

    companion object {
        fun newInstance() = GenreFragment()
        fun getIntent(context: Context, genreId: String): Intent {
            return Intent(context, GenreMovieActivity::class.java).apply {
                putExtra(Constants.GENRE_ID_TAG, genreId)
            }
        }

        const val GENRE_ID_TAG = "genre_id"
    }
}

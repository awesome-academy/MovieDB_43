package com.asterisk.tuandao.themoviedb.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.ActivityDetailBinding
import com.asterisk.tuandao.themoviedb.ui.actor.ActorActivity
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.layout_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_detail.view.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(), DetailNavigator{

    private lateinit var viewDataBinding: ActivityDetailBinding
    private lateinit var castAdapter: CastAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var youtubeVideoFragment: YouTubeVideoFragment
    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetailActivity","DetailActivity $detailViewModel")
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewModel = detailViewModel
        intent.getIntExtra(MOVIE_ID_TAG, 0).let {
            detailViewModel.setSelectedMovie(it)
        }
        youtubeVideoFragment = fragmentManager.findFragmentById(R.id.fragmentTrailer) as YouTubeVideoFragment
        initActionBar()
        initAdapter()
        initComponent()
    }

    private fun initActionBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
    }

    private fun initAdapter() {
        castAdapter = CastAdapter(ArrayList(), detailViewModel)
        reviewAdapter = ReviewAdapter(ArrayList(), detailViewModel)
        with(viewDataBinding.movieDetail) {
            recyclerCast.layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            recyclerCast.setHasFixedSize(true)
            recyclerCast.adapter = castAdapter
            recyclerReview.layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            recyclerReview.setHasFixedSize(true)
            recyclerReview.adapter = reviewAdapter
        }
    }

    private fun initComponent() {
        doObserve()
        doObserverClickedActor()
    }


    private fun doObserve() {
        detailViewModel.selectedMovie.observe(this, Observer {
            detailViewModel.getMoviesByMovie(it)
            detailViewModel.isFavorite(it)
        })
        detailViewModel.movie?.observe(this, Observer {
            when (it) {
                is Resources.Progress -> {
                    //do something
                }
                is Resources.Success -> {
                    showSuccess(it.data)
                }
                is Resources.Failure -> {
                    showError(it.e.message)
                }
            }
        })
        detailViewModel.isFavorite.observe(this, Observer {
            viewDataBinding.movieDetail.buttonLikeFavorite.visibility = if (it) View.INVISIBLE else View.VISIBLE
            viewDataBinding.movieDetail.buttonDislikeFavorite.visibility = if (!it) View.GONE else View.VISIBLE
        })
    }

    private fun doObserverClickedActor() {
        detailViewModel.selectedActor.observe(this, Observer {
            it.getContentIfNotHandled()?.let { actorId ->
                openActorDetail(actorId)
            }
        })
    }

    private fun showSuccess(movie: Movie) {
        detailViewModel.setMovieRenderView(movie)
        youtubeVideoFragment.setVideoId(movie.videoResult?.videos?.get(0)?.key)
        movie.castResult?.casts?.let {
            castAdapter.swapCast(it)
        }
        movie.reviewResult?.results?.let {
            reviewAdapter.swapReview(it)
        }
        setBtnOnClick(movie)
    }

    private fun showError(message: String?) {
        message?.let {
            this?.showMessage(it)
        }
    }

    override fun openActorDetail(actorId: Int) {
        ActorActivity.getIntent(this, actorId).apply {
            startActivity(this)
        }
    }

    private fun setBtnOnClick(movie: Movie) {
        viewDataBinding.movieDetail.buttonLikeFavorite.setOnClickListener {
            detailViewModel.addFavorite(movie)
            showToast(ADDED_MSG)
        }
        viewDataBinding.movieDetail.buttonDislikeFavorite.setOnClickListener {
            detailViewModel.deleteFavorite(movie)
            showToast(REMOVED_MSG)

        }
    }

    fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


    companion object {
        const val MOVIE_ID_TAG = "movie_id"
        const val ADDED_MSG = "Added to favorite"
        const val REMOVED_MSG = "Removed from favorite"
        fun getIntent(context: Context, movieId: Int) = Intent(context, DetailActivity::class.java)
            .apply { putExtra(MOVIE_ID_TAG, movieId) }
    }
}

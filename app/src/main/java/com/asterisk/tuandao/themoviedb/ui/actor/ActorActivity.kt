package com.asterisk.tuandao.themoviedb.ui.actor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ActorResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.databinding.ActivityActorBinding
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.showMessage
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ActorActivity : DaggerAppCompatActivity(), ActorNavigator {

    private lateinit var viewDataBinding: ActivityActorBinding
    private lateinit var relativeMovieAdapter: RelativeMovieAdapter
    private var itemDecoration: RecyclerView.ItemDecoration? = null
    @Inject
    lateinit var actorViewModel: ActorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_actor)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = actorViewModel

        intent.getIntExtra(DetailActivity.MOVIE_ID_TAG, 0).let {
            actorViewModel.setSelectedActor(it)
        }
        initActionBar()
        initAdapter()
        initComponent()
    }

    fun initActionBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
    }

    fun initAdapter() {
        relativeMovieAdapter = RelativeMovieAdapter(ArrayList(), actorViewModel)
        with(viewDataBinding) {
            recyclerMovie.layoutManager = GridLayoutManager(this@ActorActivity, Constants.SPAN_COUNT)
            recyclerMovie.setHasFixedSize(true)
            recyclerMovie.addItemDecoration(DividerItemDecoration(this@ActorActivity, 0))
            recyclerMovie.adapter = relativeMovieAdapter
        }
    }

    fun initComponent() {
        doObserve()
    }

    private fun doObserve() {
       actorViewModel.selectedActor.observe(this, Observer {
           actorViewModel.setSelectedActor(it)
       })
       actorViewModel.actorDetail.observe(this, Observer {
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
    }

    private fun showSuccess(data: ActorResponse?) {
        data?.let {
            it.movies?.let { movies -> relativeMovieAdapter.swapAdapter(movies) }
            while (viewDataBinding.recyclerMovie.itemDecorationCount > 0
                && (viewDataBinding.recyclerMovie.getItemDecorationAt(0)?.let { itemDecoration = it }) != null
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

    override fun openGenreMovies(genreId: String) {

    }

    companion object {
        const val ACTOR_ID_TAG = "actor_id"
        fun getIntent(context: Context, movieId: Int) = Intent(context, ActorActivity::class.java)
            .apply { putExtra(ACTOR_ID_TAG, movieId) }
    }
}

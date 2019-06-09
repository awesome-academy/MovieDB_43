package com.asterisk.tuandao.themoviedb.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.asterisk.tuandao.themoviedb.data.source.remote.paging.MoviesDataSourceFactory
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.Event
import com.bumptech.glide.Glide.init
import javax.inject.Inject

class HomeViewModel @Inject constructor
    (
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {
    var typeMovie: HashMap<Int,String> = HashMap()
    init {
        typeMovie[Constants.KEY_POPULAR_MOVIE] = ""
    }
    private val movieResult = moviesRepository.getLoadMoviesWithPages(typeMovie)
    val movies = Transformations.switchMap(movieResult, { it.pagedList })
    val networkState = Transformations.switchMap(movieResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(movieResult, { it.refreshState })!!

    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent
    private val _openSearchEvent = MutableLiveData<Event<Unit>>()
    val openSearchEvent: LiveData<Event<Unit>>
        get() = _openSearchEvent

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    fun openSearchMovie() {
        _openSearchEvent.value = Event(Unit)
    }

    fun retry() {
        val listing = movieResult?.value
        listing?.retry?.invoke()
    }

    fun refresh() {
        movieResult.value?.refresh?.invoke()
    }
    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

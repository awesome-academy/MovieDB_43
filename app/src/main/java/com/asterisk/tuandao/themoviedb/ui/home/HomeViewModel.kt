package com.asterisk.tuandao.themoviedb.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class HomeViewModel @Inject constructor
    (
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {

    private val _movies: MutableLiveData<Resources<MovieResponse>> by lazy {
        MutableLiveData<Resources<MovieResponse>>().also {
            getMovies(it)
        }
    }
    val movie: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent

    fun getMovies(mutableLiveData: MutableLiveData<Resources<MovieResponse>>) {
        _movies.value = Resources.loading(true)
        compositeDisposable.add(
            moviesRepository.getMovies(DEFAULT_PAGE).handleData(mutableLiveData)
        )
    }

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

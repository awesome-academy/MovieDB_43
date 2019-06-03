package com.asterisk.tuandao.themoviedb.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor
    (
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {

    private val _movies = MutableLiveData<Resources<MovieResponse>>()
    val movie: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent

    fun getMovies() {
        _movies.value = Resources.loading(true)
        compositeDisposable.add(
            moviesRepository.getMovies(DEFAULT_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _movies.value = Resources.success(it)
                    },
                    {
                        _movies.value = Resources.failure(it)
                    }
                )
        )
    }

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

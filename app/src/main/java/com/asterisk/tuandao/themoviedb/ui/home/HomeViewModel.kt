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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor
    (
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {

    private var currentPage = 1
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
    private val _openSearchEvent = MutableLiveData<Event<Unit>>()
    val openSearchEvent: LiveData<Event<Unit>>
        get() = _openSearchEvent
    private val _loadMore = MutableLiveData<Boolean>()
    val loadMore: LiveData<Boolean>
        get() = _loadMore

    fun getMovies(mutableLiveData: MutableLiveData<Resources<MovieResponse>>) {
        compositeDisposable.add(
            moviesRepository.getMovies(currentPage).handleData(mutableLiveData)
        )
    }

    fun getMoreMovies() {
        _movies.value = Resources.loading(true)
        compositeDisposable.add(
            moviesRepository.getMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _movies.value = Resources.success(it)
                    _loadMore.value = false
                }, {
                    _movies.value = Resources.failure(it)
                    _loadMore.value = false
                })
        )
    }

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    fun openSearchMovie() {
        _openSearchEvent.value = Event(Unit)
    }

    fun increareCurrentPage() {
        currentPage++
    }

    fun setLoadMore(status: Boolean) {
        _loadMore.value = status
    }

    companion object {
    }
}

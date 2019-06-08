package com.asterisk.tuandao.themoviedb.ui.search

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

class SearchViewModel @Inject constructor(
    application: Application, val repository: MoviesRepository
) : BaseViewModel(application) {
    private val _movies = MutableLiveData<Resources<MovieResponse>>()
    val movies: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _movieDetail = MutableLiveData<Event<Int>>()
    val movieDetail: LiveData<Event<Int>>
        get() = _movieDetail

    fun searchMovies(query: String, page: Int) {
       compositeDisposable.add(
           repository.searchMovieByName(query, PAGE_DEFAULT).handleData(_movies)
       )
    }

    fun openMovieDetail(movieId: Int) {
        _movieDetail.value = Event(movieId)
    }

    companion object {
        const val PAGE_DEFAULT = 1
    }
}

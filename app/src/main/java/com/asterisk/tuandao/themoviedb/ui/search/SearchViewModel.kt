package com.asterisk.tuandao.themoviedb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.Event
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    application: Application, val repository: MoviesRepository
) : BaseViewModel(application) {
    var typeMovie: HashMap<Int,String> = HashMap()

    private val _selectedKey = MutableLiveData<String>()
    val selectedKey: LiveData<String>
        get() = _selectedKey
    private val movieResult = Transformations.switchMap(selectedKey) {
        typeMovie[Constants.KEY_SEARCH_MOVIE] = it
        repository.getLoadMoviesWithPages(typeMovie)
    }

    val movies = Transformations.switchMap(movieResult,{it.pagedList})
    val networkState = Transformations.switchMap(movieResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(movieResult, { it.refreshState })!!

    private val _movieDetail = MutableLiveData<Event<Int>>()
    val movieDetail: LiveData<Event<Int>>
        get() = _movieDetail

    fun openMovieDetail(movieId: Int) {
        _movieDetail.value = Event(movieId)
    }

    fun setKeySearch(query: String) {
        _selectedKey.value = query
    }

    fun retry() {
        val listing = movieResult?.value
        listing?.retry?.invoke()
    }

    fun refresh() {
        movieResult.value?.refresh?.invoke()
    }

    companion object {
        const val PAGE_DEFAULT = 1
    }
}

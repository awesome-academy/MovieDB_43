package com.asterisk.tuandao.themoviedb.ui.search

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

class SearchViewModel @Inject constructor(
        application: Application, val repository: MoviesRepository
) : BaseViewModel(application) {
    private var currentPage = 1
    private lateinit var key: String
    private val _movies = MutableLiveData<Resources<MovieResponse>>()
    val movies: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _movieDetail = MutableLiveData<Event<Int>>()
    val movieDetail: LiveData<Event<Int>>
        get() = _movieDetail
    private val _loadMore = MutableLiveData<Boolean>()
    val loadMore: LiveData<Boolean>
        get() = _loadMore

    fun searchMovies() {
        compositeDisposable.add(repository.searchMovieByName(key, currentPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _movies.value = Resources.success(it)
                            _loadMore.value = false
                        }, {
                            _movies.value = Resources.failure(it)
                            _loadMore.value = false
                        }))
    }

    fun openMovieDetail(movieId: Int) {
        _movieDetail.value = Event(movieId)
    }

    fun increareCurrentPage() {
        currentPage++
    }

    fun setLoadMore(status: Boolean) {
        _loadMore.value = status
    }

    fun setKey(key: String) {
        this.key = key
    }

    companion object {
        const val PAGE_DEFAULT = 1
    }
}

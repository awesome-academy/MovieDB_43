package com.asterisk.tuandao.themoviedb.ui.movies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.ui.home.HomeViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import com.asterisk.tuandao.themoviedb.util.handleData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GenreMovieViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository) : BaseViewModel(application){
    private var currentPage = 1

    private val _movies = MutableLiveData<Resources<MovieResponse>>()
    val movie: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _selectedGenre = MutableLiveData<String>()
    val selectedGenre: LiveData<String>
        get() = _selectedGenre
    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent
    private val _loadMore = MutableLiveData<Boolean>()
    val loadMore: LiveData<Boolean>
        get() = _loadMore

    //bug rotate activity, have not perform yet
    fun getMoviesByGenre(genreId: String) {
        compositeDisposable.add(
           moviesRepository.getMoviesByGenre(currentPage, genreId)
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

    fun setSelectedGenre(genreId: String) {
        _selectedGenre.value = genreId
    }

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    fun increareCurrentPage() {
        currentPage++
    }

    fun setLoadMore(status: Boolean) {
        _loadMore.value = status
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}
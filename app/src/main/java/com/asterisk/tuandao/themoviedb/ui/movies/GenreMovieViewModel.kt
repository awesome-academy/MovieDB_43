package com.asterisk.tuandao.themoviedb.ui.movies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.Event
import javax.inject.Inject

class GenreMovieViewModel @Inject constructor(
        application: Application,
        val moviesRepository: MoviesRepository) : BaseViewModel(application) {
    var typeMovie: HashMap<Int, String> = HashMap()
    private val _selectedGenre = MutableLiveData<String>()
    val selectedGenre: LiveData<String>
        get() = _selectedGenre

    private val movieResult = Transformations.switchMap(selectedGenre) {
        when (it) {
            "1" -> typeMovie[Constants.KEY_PlAYING_MOVIE] = "1"
            "2" -> typeMovie[Constants.KEY_TOP_MOVIE] = "2"
            "3" -> typeMovie[Constants.KEY_COMING_MOVIE] = "3"
            else -> typeMovie[Constants.KEY_GENRE_MOVIE] = it
        }
        moviesRepository.getLoadMoviesWithPages(typeMovie)
    }
    val movies = Transformations.switchMap(movieResult, { it.pagedList })
    val networkState = Transformations.switchMap(movieResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(movieResult, { it.refreshState })!!
    // load data genre

    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent

    fun setSelectedGenre(genreId: String) {
        _selectedGenre.value = genreId
    }

    fun openMovieDetail(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
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

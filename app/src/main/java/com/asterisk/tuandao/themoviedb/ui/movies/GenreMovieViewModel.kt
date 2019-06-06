package com.asterisk.tuandao.themoviedb.ui.movies

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

class GenreMovieViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository) : BaseViewModel(application){

    private val _movies = MutableLiveData<Resources<MovieResponse>>()
    val movie: LiveData<Resources<MovieResponse>>
        get() = _movies
    private val _selectedGenre = MutableLiveData<String>()
    val selectedGenre: LiveData<String>
        get() = _selectedGenre
    private val _openMovieEvent = MutableLiveData<Event<Int>>()
    val openMovieEvent: LiveData<Event<Int>>
        get() = _openMovieEvent

    //bug rotate activity, have not perform yet
    fun getMoviesByGenre(genreId: String) {
        compositeDisposable.add(
           moviesRepository.getMoviesByGenre(DEFAULT_PAGE, genreId).handleData(_movies)
        )
    }

    fun setSelectedGenre(genreId: String) {
        _selectedGenre.value = genreId
    }

    fun openDetailMovie(movieId: Int) {
        _openMovieEvent.value = Event(movieId)
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}

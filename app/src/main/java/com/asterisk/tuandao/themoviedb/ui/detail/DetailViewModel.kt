package com.asterisk.tuandao.themoviedb.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class DetailViewModel
@Inject constructor(application: Application, val moviesRepository: MoviesRepository)
    : BaseViewModel(application) {
    private val _movie = MutableLiveData<Resources<Movie>>()
    val movie: LiveData<Resources<Movie>>
        get() = _movie
    private val _selectedMovie = MutableLiveData<Int>()
    val selectedMovie: LiveData<Int>
        get() = _selectedMovie

    fun setSelectedMovie(movieId: Int) {
        _selectedMovie.value = movieId
    }

    fun getMoviesByGenre(movieId: Int) {
        compositeDisposable.add(
                moviesRepository.getMoviesById(movieId, APPEND_TO_MOVIE_DETAIL).handleData(_movie)
        )
    }

    companion object {
        private const val APPEND_TO_MOVIE_DETAIL = "videos,credits,reviews"
    }
}
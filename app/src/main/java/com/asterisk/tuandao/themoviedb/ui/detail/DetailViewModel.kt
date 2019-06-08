package com.asterisk.tuandao.themoviedb.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class DetailViewModel
@Inject constructor(application: Application, val moviesRepository: MoviesRepository)
    : BaseViewModel(application) {
    private val _movie = MutableLiveData<Resources<Movie>>()
    val movie: LiveData<Resources<Movie>>
        get() = _movie
    private val _movieRenderView = MutableLiveData<Movie>()
    val movieRenderView: LiveData<Movie>
        get() = _movieRenderView
    private val _selectedMovie = MutableLiveData<Int>()
    val selectedMovie: LiveData<Int>
        get() = _selectedMovie
    private val _selectedActor = MutableLiveData<Event<Int>>()
    val selectedActor: LiveData<Event<Int>>
        get() = _selectedActor

    fun getMoviesByMovie(movieId: Int) {
        compositeDisposable.add(
                moviesRepository.getMoviesById(movieId, APPEND_TO_MOVIE_DETAIL).handleData(_movie)
        )
    }

    fun setSelectedMovie(movieId: Int) {
        _selectedMovie.value = movieId
    }

    fun setSelectedActor(actorId: Int) {
        _selectedActor.value = Event(actorId)
    }

    fun setMovieRenderView(movie: Movie) {
       _movieRenderView.value = movie
    }

    companion object {
        private const val APPEND_TO_MOVIE_DETAIL = "videos,credits,reviews"
    }
}

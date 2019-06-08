package com.asterisk.tuandao.themoviedb.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {
    val favorite = moviesRepository.getAllFavorite()
    private val _movieDetail = MutableLiveData<Event<Int>>()
    val movieDetail: LiveData<Event<Int>>
        get() = _movieDetail

    fun openMovieDetail(movieId: Int) {
        _movieDetail.value = Event(movieId)
    }
}
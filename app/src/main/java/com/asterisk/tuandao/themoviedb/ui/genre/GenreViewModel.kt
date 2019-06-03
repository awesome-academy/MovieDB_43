package com.asterisk.tuandao.themoviedb.ui.genre

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {
    private val _genres = MutableLiveData<Resources<GenreResponse>>()
    val genres: LiveData<Resources<GenreResponse>>
        get() = _genres

    init {
        //some bug with thread, have not performed yet
        getGenres()
    }

    private fun getGenres() {
        compositeDisposable.add(
            moviesRepository.getGenres().handleData(_genres)
        )
    }
}

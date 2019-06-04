package com.asterisk.tuandao.themoviedb.ui.genre

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        getGenreList()
    }

    fun getGenreList() {
        compositeDisposable.add(
            moviesRepository.getGenreList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _genres.value = Resources.success(it)
                    },
                    {
                        _genres.value = Resources.failure(it)
                    }
                )
        )
    }
}

package com.asterisk.tuandao.themoviedb.ui.main

import android.app.Application
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {

}

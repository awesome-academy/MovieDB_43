package com.asterisk.tuandao.themoviedb.ui.main

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class MainModule {
    @MainActivityScope
    @Provides
    fun provideViewModel(mainActivity: MainActivity, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(mainActivity, viewModelFactory).get(MainViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class MainActivityScope

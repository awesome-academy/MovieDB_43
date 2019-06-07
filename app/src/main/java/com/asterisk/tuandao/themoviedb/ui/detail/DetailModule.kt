package com.asterisk.tuandao.themoviedb.ui.detail

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class DetailModule {
    @DetailActivityScope
    @Provides
    fun provideViewModel(detailActivity: DetailActivity, viewModelFactory: MovieViewModelFactory) =
            ViewModelProviders.of(detailActivity, viewModelFactory).get(DetailViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailActivityScope

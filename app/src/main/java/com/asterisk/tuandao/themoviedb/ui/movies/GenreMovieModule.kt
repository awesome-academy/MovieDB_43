package com.asterisk.tuandao.themoviedb.ui.movies

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class GenreMovieModule {
    @GenreMovieActivityScope
    @Provides
    fun provideViewModel(genreMovieActivity: GenreMovieActivity, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(genreMovieActivity, viewModelFactory).get(GenreMovieViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class GenreMovieActivityScope

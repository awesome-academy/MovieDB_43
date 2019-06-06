package com.asterisk.tuandao.themoviedb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asterisk.tuandao.themoviedb.ui.detail.DetailViewModel
import com.asterisk.tuandao.themoviedb.ui.genre.GenreViewModel
import com.asterisk.tuandao.themoviedb.ui.home.HomeViewModel
import com.asterisk.tuandao.themoviedb.ui.main.MainViewModel
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieViewModel
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenreViewModel::class)
    abstract fun bindGenreViewModel(genreViewModel: GenreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenreMovieViewModel::class)
    abstract fun bindGenreMovieViewModel(genreMovieViewModel: GenreMovieViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory
}

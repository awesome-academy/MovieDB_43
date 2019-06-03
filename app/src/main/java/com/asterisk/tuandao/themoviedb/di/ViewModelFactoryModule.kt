package com.asterisk.tuandao.themoviedb.di

import androidx.lifecycle.ViewModelProvider
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory
}

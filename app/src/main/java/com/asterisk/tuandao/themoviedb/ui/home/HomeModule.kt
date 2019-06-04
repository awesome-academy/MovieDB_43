package com.asterisk.tuandao.themoviedb.ui.home

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class HomeModule {
//    @HomeFragmentScope
//    @Provides
//    fun provideViewModel(homeFragment: HomeFragment, viewModelFactory: MovieViewModelFactory) =
//        ViewModelProviders.of(homeFragment, viewModelFactory).get(HomeViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeFragmentScope

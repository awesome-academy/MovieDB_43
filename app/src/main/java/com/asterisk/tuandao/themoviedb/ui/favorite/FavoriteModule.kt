package com.asterisk.tuandao.themoviedb.ui.favorite

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class FavoriteModule {
    @FavoriteFragmentScope
    @Provides
    fun provideViewModel(favoriteFragment: FavoriteFragment, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(favoriteFragment, viewModelFactory).get(FavoriteViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoriteFragmentScope

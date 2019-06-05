package com.asterisk.tuandao.themoviedb.ui.genre

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class GenreModule {
    @GenreFragmentScope
    @Provides
    fun provideViewModel(genreFragment: GenreFragment, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(genreFragment, viewModelFactory).get(GenreViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class GenreFragmentScope

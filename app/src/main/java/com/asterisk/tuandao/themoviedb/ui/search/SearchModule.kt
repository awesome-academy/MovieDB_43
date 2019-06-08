package com.asterisk.tuandao.themoviedb.ui.search

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class SearchModule {
    @SearchActivityScope
    @Provides
    fun provideViewModel(searchActivity: SearchActivity, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(searchActivity, viewModelFactory).get(SearchViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchActivityScope

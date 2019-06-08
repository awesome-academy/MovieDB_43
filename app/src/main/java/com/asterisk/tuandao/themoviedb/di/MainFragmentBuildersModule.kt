package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.ui.favorite.FavoriteFragment
import com.asterisk.tuandao.themoviedb.ui.favorite.FavoriteModule
import com.asterisk.tuandao.themoviedb.ui.genre.GenreFragment
import com.asterisk.tuandao.themoviedb.ui.genre.GenreFragmentScope
import com.asterisk.tuandao.themoviedb.ui.genre.GenreModule
import com.asterisk.tuandao.themoviedb.ui.home.HomeFragment
import com.asterisk.tuandao.themoviedb.ui.home.HomeFragmentScope
import com.asterisk.tuandao.themoviedb.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @HomeFragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @GenreFragmentScope
    @ContributesAndroidInjector(modules = [GenreModule::class])
    abstract fun contributeGenreFragment(): GenreFragment

    @GenreFragmentScope
    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}

package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.ui.actor.ActorActivity
import com.asterisk.tuandao.themoviedb.ui.actor.ActorActivityScope
import com.asterisk.tuandao.themoviedb.ui.actor.ActorModule
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivity
import com.asterisk.tuandao.themoviedb.ui.detail.DetailActivityScope
import com.asterisk.tuandao.themoviedb.ui.detail.DetailModule
import com.asterisk.tuandao.themoviedb.ui.main.MainActivity
import com.asterisk.tuandao.themoviedb.ui.main.MainActivityScope
import com.asterisk.tuandao.themoviedb.ui.main.MainModule
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieActivity
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieActivityScope
import com.asterisk.tuandao.themoviedb.ui.movies.GenreMovieModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainModule::class])
    abstract fun contributeHomeActivity(): MainActivity

    @DetailActivityScope
    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun contributeDetailActivity(): DetailActivity

    @GenreMovieActivityScope
    @ContributesAndroidInjector(modules = [GenreMovieModule::class])
    abstract fun contributeGenreMovieAcitivity(): GenreMovieActivity

    @ActorActivityScope
    @ContributesAndroidInjector(modules = [ActorModule::class])
    abstract fun contributeActorAcitivity(): ActorActivity
}

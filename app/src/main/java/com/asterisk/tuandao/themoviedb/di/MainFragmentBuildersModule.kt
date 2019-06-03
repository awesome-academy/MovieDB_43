package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment
}

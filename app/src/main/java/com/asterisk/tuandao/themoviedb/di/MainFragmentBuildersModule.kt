package com.asterisk.tuandao.themoviedb.di

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
}

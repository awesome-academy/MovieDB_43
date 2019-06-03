package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.di.scope.HomeScope
import com.asterisk.tuandao.themoviedb.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): MainActivity
}

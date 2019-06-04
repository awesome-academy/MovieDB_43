package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.ui.main.MainActivity
import com.asterisk.tuandao.themoviedb.ui.main.MainActivityScope
import com.asterisk.tuandao.themoviedb.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainModule::class])
    abstract fun contributeHomeActivity(): MainActivity
}


package com.asterisk.tuandao.themoviedb.di

import com.asterisk.tuandao.themoviedb.di.scope.HomeScope
import com.asterisk.tuandao.themoviedb.ui.home.HomeActivity
import com.asterisk.tuandao.themoviedb.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}

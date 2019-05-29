package com.asterisk.tuandao.themoviedb.di

import android.app.Application
import com.asterisk.tuandao.themoviedb.di.scope.ApplicationModule
import com.asterisk.tuandao.themoviedb.util.MovieApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ApplicationModule::class]
)
interface AppComponent : AndroidInjector<MovieApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

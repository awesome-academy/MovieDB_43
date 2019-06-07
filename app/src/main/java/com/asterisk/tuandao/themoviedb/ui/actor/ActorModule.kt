package com.asterisk.tuandao.themoviedb.ui.actor

import androidx.lifecycle.ViewModelProviders
import com.asterisk.tuandao.themoviedb.util.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Scope

@Module
class ActorModule {
    @ActorActivityScope
    @Provides
    fun provideViewModel(actorActivity: ActorActivity, viewModelFactory: MovieViewModelFactory) =
        ViewModelProviders.of(actorActivity, viewModelFactory).get(ActorViewModel::class.java)
}

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActorActivityScope

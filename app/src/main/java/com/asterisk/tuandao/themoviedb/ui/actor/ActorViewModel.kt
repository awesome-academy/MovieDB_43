package com.asterisk.tuandao.themoviedb.ui.actor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ActorResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.Resources
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.ui.base.BaseViewModel
import com.asterisk.tuandao.themoviedb.util.Event
import com.asterisk.tuandao.themoviedb.util.handleData
import javax.inject.Inject

class ActorViewModel @Inject constructor(
    application: Application,
    val moviesRepository: MoviesRepository
) : BaseViewModel(application) {
    private val _actorDetail = MutableLiveData<Resources<ActorResponse>>()
    val actorDetail: LiveData<Resources<ActorResponse>>
        get() = _actorDetail
    private val _actorRenderView = MutableLiveData<ActorResponse>()
    val actorRenderView: LiveData<ActorResponse>
        get() = _actorRenderView
    private val _selectedActor = MutableLiveData<Int>()
    val selectedActor: LiveData<Int>
        get() = _selectedActor
    private val _selectedMovie = MutableLiveData<Event<Int>>()
    val selectedMovie: LiveData<Event<Int>>
        get() = _selectedMovie

    fun getActorDetail(personId: Int) {
        compositeDisposable.add(
            moviesRepository.getPersonById(personId, APPEND_TO_MOVIE_DETAIL).handleData(_actorDetail)
        )
    }

    fun setSelectedActor(actorId: Int) {
        _selectedActor.value = actorId
    }

    fun setMovieRenderView(actorResponse: ActorResponse) {
        _actorRenderView.value = actorResponse
    }

    fun setSelectedMovie(movieId: Int) {
        _selectedMovie.value = Event(movieId)
    }

    companion object {
        private const val APPEND_TO_MOVIE_DETAIL = "movie_credits"
    }
}

package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor(private val movieService: MoviesApi) : MoviesDataSource.Remote {

    override fun getMovies(page: Int): Single<MovieResponse> {
        return movieService.getPopularMovies(page)
    }

}

package com.asterisk.tuandao.themoviedb.data.source.repository

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(val moviesRemoteDataSource: MoviesDataSource.Remote)
    : MoviesDataSource.Local, MoviesDataSource.Remote{

    override fun getMovies(page: Int) : Single<MovieResponse> {
        return moviesRemoteDataSource.getMovies(page)
    }
}

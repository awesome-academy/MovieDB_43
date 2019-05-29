package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor(val movieService: MoviesApi) : MoviesDataSource.Remote {
}

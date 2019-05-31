package com.asterisk.tuandao.themoviedb.data.source.repository

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(val moviesRemoteDataSource: MoviesDataSource.Remote) : MoviesDataSource.Local,
    MoviesDataSource.Remote {
}

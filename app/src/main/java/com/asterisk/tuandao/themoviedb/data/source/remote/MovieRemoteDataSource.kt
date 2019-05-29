package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.MovieDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(val movieService: MovieService) : MovieDataSource.Remote {
}

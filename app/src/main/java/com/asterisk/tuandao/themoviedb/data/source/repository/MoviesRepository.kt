package com.asterisk.tuandao.themoviedb.data.source.repository

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ActorResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(val moviesRemoteDataSource: MoviesDataSource.Remote)
    : MoviesDataSource.Local, MoviesDataSource.Remote {

    override fun getMovies(page: Int) = moviesRemoteDataSource.getMovies(page)

    override fun getGenreList() = moviesRemoteDataSource.getGenreList()

    override fun getMoviesByGenre(page: Int, genreId: String) = moviesRemoteDataSource.getMoviesByGenre(page, genreId)

    override fun getMoviesById(page: Int, append: String) = moviesRemoteDataSource.getMoviesById(page, append)

    override fun getPersonById(personId: Int, append: String) = moviesRemoteDataSource.getPersonById(personId, append)

    override fun searchMovieByName(key: String, page: Int) = moviesRemoteDataSource.searchMovieByName(key, page)
}

package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor(val movieApi: MoviesApi) : MoviesDataSource.Remote {

    override fun getMovies(page: Int) = movieApi.getPopularMovies(page)

    override fun getGenres() = movieApi.getGenres()

    override fun getMoviesByGenre(page: Int, genreId: String) = movieApi.getMoviesByGenre(page, genreId)

    override fun getMoviesById(page: Int, apppend: String) = movieApi.getMoviesById(page, apppend)

    override fun getPersonById(personId: Int, append: String) = movieApi.getPersonById(personId, append)

    override fun searchMovieByName(key: String, page: Int) = movieApi.searchMovieByName(key, page)

}

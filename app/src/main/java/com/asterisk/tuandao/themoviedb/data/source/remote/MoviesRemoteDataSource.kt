package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor(val movieApi: MoviesApi) : MoviesDataSource.Remote {

    override fun getMovies(page: Int) = movieApi.getPopularMovies(page)

    override fun getGenreList() = movieApi.getGenresList()

    override fun getMoviesByGenre(page: Int, genreId: String) = movieApi.getMoviesByGenre(page, genreId)

    override fun getMoviesById(page: Int, apppend: String) = movieApi.getMoviesById(page, apppend)
}

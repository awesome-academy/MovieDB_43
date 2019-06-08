package com.asterisk.tuandao.themoviedb.data.source.repository

import androidx.lifecycle.LiveData
import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.local.MoviesLocalDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(val moviesLocalDataSource: MoviesLocalDataSource,val moviesRemoteDataSource: MoviesDataSource.Remote)
    : MoviesDataSource.Local, MoviesDataSource.Remote {

    override fun getMovies(page: Int) = moviesRemoteDataSource.getMovies(page)

    override fun getGenres() = moviesRemoteDataSource.getGenres()

    override fun getMoviesByGenre(page: Int, genreId: String) = moviesRemoteDataSource.getMoviesByGenre(page, genreId)

    override fun getMoviesById(page: Int, append: String) = moviesRemoteDataSource.getMoviesById(page, append)

    override fun getPersonById(personId: Int, append: String) = moviesRemoteDataSource.getPersonById(personId, append)

    override fun searchMovieByName(key: String, page: Int) = moviesRemoteDataSource.searchMovieByName(key, page)

    override fun getAllFavorite(): LiveData<List<Movie>> = moviesLocalDataSource.getAllFavorite()

    override fun addFavorite(movie: Movie) = moviesLocalDataSource.addFavorite(movie)

    override fun deleteFavorite(movie: Movie) = moviesLocalDataSource.deleteFavorite(movie)

    override fun getFavoriteById(movieId: Int) = moviesLocalDataSource.getFavoriteById(movieId)

}

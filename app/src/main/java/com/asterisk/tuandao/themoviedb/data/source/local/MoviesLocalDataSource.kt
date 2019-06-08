package com.asterisk.tuandao.themoviedb.data.source.local

import androidx.lifecycle.LiveData
import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesLocalDataSource @Inject constructor(val movieDao: MovieDao) : MoviesDataSource.Local{

    override fun getAllFavorite(): LiveData<List<Movie>> = movieDao.getAllFavorite()

    override fun addFavorite(movie: Movie) = movieDao.addFavorite(movie)

    override fun deleteFavorite(movie: Movie) = movieDao.deleteFavorite(movie)

    override fun getFavoriteById(id: Int): Movie = movieDao.getFavoriteById(id)
}

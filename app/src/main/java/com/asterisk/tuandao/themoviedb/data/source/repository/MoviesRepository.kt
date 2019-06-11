package com.asterisk.tuandao.themoviedb.data.source.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.local.MoviesLocalDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.Listing
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.remote.paging.MoviesDataSourceFactory
import com.asterisk.tuandao.themoviedb.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    val moviesLocalDataSource: MoviesLocalDataSource,
    val moviesRemoteDataSource: MoviesDataSource.Remote
) : MoviesDataSource.Local, MoviesDataSource.Remote {

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

    fun getLoadMoviesWithPages(typeMovie: HashMap<Int,String>): LiveData<Listing<Movie>> {
        val sourceFactory = MoviesDataSourceFactory(this, typeMovie)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(40)
            .build()
        val moviePagedList = LivePagedListBuilder<Int, Movie>(sourceFactory, config).build()
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        val mutableLiveData = MutableLiveData<Listing<Movie>>()
        val livedata: LiveData<Listing<Movie>> = mutableLiveData
        mutableLiveData.value = Listing(
            pagedList = moviePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
        return livedata
    }
}

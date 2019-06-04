package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResponse>

    @GET("3/genre/movie/list")
    fun getGenresList(): Single<GenreResponse>

    @GET("/3/discover/movie")
    fun getMoviesByGenre(
        @Query("page") page: Int
        , @Query("with_genres") genreId: String
    ): Single<MovieResponse>
}

package com.asterisk.tuandao.themoviedb.data.source.remote

import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ActorResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResponse>

    @GET("3/genre/movie/list")
    fun getGenres(): Single<GenreResponse>

    @GET("/3/discover/movie")
    fun getMoviesByGenre(
        @Query("page") page: Int
        , @Query("with_genres") genreId: String
    ): Single<MovieResponse>

    @GET("/3/movie/{id}")
    fun getMoviesById(
        @Path("id") movieId: Int,
        @Query("append_to_response") append: String
    ): Single<Movie>

    @GET("/3/person/{person_id}")
    fun getPersonById(
        @Path("person_id") personId: Int,
        @Query("append_to_response") append: String
    ): Single<ActorResponse>

    @GET("/3/search/movie")
    fun searchMovieByName(
        @Query("query") key: String,
        @Query("page") page: Int
    ): Single<MovieResponse>

    @GET("3/movie/now_playing")
    fun getPlayingMovies(): Single<MovieResponse>

    @GET("3/movie/top_rated")
    fun getTopMovies(): Single<MovieResponse>

    @GET("3/movie/upcoming")
    fun getComingMovies(): Single<MovieResponse>
}

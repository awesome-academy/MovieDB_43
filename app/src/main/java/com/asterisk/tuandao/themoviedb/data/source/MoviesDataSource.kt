package com.asterisk.tuandao.themoviedb.data.source

import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ActorResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single

interface MoviesDataSource {

    interface Local {

    }

    interface Remote {
        fun getMovies(page: Int): Single<MovieResponse>
        fun getGenreList(): Single<GenreResponse>
        fun getMoviesByGenre(page: Int, genreId: String): Single<MovieResponse>
        fun getMoviesById(page: Int, append: String): Single<Movie>
        fun getPersonById(personId: Int, append: String): Single<ActorResponse>
        fun searchMovieByName(key: String, page: Int): Single<MovieResponse>
    }
}

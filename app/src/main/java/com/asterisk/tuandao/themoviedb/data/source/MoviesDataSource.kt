package com.asterisk.tuandao.themoviedb.data.source

import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import io.reactivex.Single

interface MoviesDataSource {

    interface Local {

    }

    interface Remote {
        fun getMovies(page: Int): Single<MovieResponse>
        fun getGenres(): Single<GenreResponse>
        fun getMoviesByGenre(page: Int, genreId: String): Single<MovieResponse>
    }
}

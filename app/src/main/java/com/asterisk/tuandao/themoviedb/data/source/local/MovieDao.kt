package com.asterisk.tuandao.themoviedb.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.asterisk.tuandao.themoviedb.data.source.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(movie: Movie)

    @Delete
    fun deleteFavorite(movie: Movie)

    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun getAllFavorite(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getFavoriteById(id: Int): Movie
}

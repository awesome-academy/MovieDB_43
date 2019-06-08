package com.asterisk.tuandao.themoviedb.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asterisk.tuandao.themoviedb.data.source.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}
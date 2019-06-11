package com.asterisk.tuandao.themoviedb.data.source.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository

class MoviesDataSourceFactory (val repository: MoviesRepository, val typeMovie: HashMap<Int,String>) : DataSource.Factory<Int, Movie>() {
    val sourceLiveData = MutableLiveData<MoviesPagedDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MoviesPagedDataSource(repository, typeMovie)
        sourceLiveData.postValue(source)
        return source
    }
}



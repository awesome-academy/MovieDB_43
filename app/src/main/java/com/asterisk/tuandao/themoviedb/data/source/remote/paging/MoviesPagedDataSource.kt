package com.asterisk.tuandao.themoviedb.data.source.remote.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.asterisk.tuandao.themoviedb.data.source.model.respone.MovieResponse
import com.asterisk.tuandao.themoviedb.data.source.remote.NetworkState
import com.asterisk.tuandao.themoviedb.data.source.repository.MoviesRepository
import com.asterisk.tuandao.themoviedb.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesPagedDataSource(val moviesRepository: MoviesRepository, val typeMovie: HashMap<Int,String>) :
    PageKeyedDataSource<Int, Movie>() {

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private val compositeDisposable by lazy { CompositeDisposable() }
    private var pageNumber = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
       loadInitialDataByType(params, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
       loadAfterDataByType(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

    private fun onShowFetched(movies: MovieResponse?, callback: LoadInitialCallback<Int, Movie>) {
        movies?.results?.let {
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            pageNumber++
            callback.onResult(it, null, pageNumber)
        }
    }

    private fun onMoreShowFetched(movies: MovieResponse?, callback: LoadCallback<Int, Movie>) {
        movies?.results?.let {
            networkState.postValue(NetworkState.LOADED)
            retry = null
            pageNumber++
            callback.onResult(it, pageNumber)
        }
    }

    private fun performErrorInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>,
        error: Throwable?
    ) {
        retry = {
            loadInitial(params, callback)
        }
        val error = NetworkState.error(error?.message ?: "unknown error")
        networkState.postValue(error)
        initialLoad.postValue(error)
    }

    private fun performErrorLoadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>, error: Throwable?) {
        retry = {
            loadAfter(params, callback)
        }
        networkState.postValue(NetworkState.error(error?.message ?: "unknown err"))
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            it.invoke()
        }
    }

    private fun loadInitialDataByType(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        if (typeMovie.containsKey(Constants.KEY_POPULAR_MOVIE)) {
            getInitialPopularMovies(params, callback)
        }
        if (typeMovie.containsKey(Constants.KEY_GENRE_MOVIE)) {
            typeMovie[Constants.KEY_GENRE_MOVIE]?.let {
                getInitalGenreMovies(params, callback, it)
            }
        }
        if (typeMovie.containsKey(Constants.KEY_SEARCH_MOVIE)) {
            typeMovie[Constants.KEY_SEARCH_MOVIE]?.let {
                getInitalSearchMovies(params, callback, it)
            }
        }
        if (typeMovie.containsKey(Constants.KEY_PlAYING_MOVIE)) {
            getInitalPlayingMovies(params,callback)
        }
        if (typeMovie.containsKey(Constants.KEY_COMING_MOVIE)) {
            getInitalComingMovies(params,callback)
        }
        if (typeMovie.containsKey(Constants.KEY_TOP_MOVIE)) {
            getInitalTopMovies(params,callback)
        }
    }

    private fun loadAfterDataByType(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>){
        if (typeMovie.containsKey(Constants.KEY_POPULAR_MOVIE)) {
            getLoadAfterPopularMovies(params, callback)
        }
        if (typeMovie.containsKey(Constants.KEY_GENRE_MOVIE)) {
            typeMovie[Constants.KEY_GENRE_MOVIE]?.let {
                getLoadAfterGenreMovies(params, callback, it)
            }
        }
        if (typeMovie.containsKey(Constants.KEY_SEARCH_MOVIE)) {
            typeMovie[Constants.KEY_SEARCH_MOVIE]?.let {
                getLoadAfterSearchMovies(params, callback, it)
            }
        }
        if (typeMovie.containsKey(Constants.KEY_COMING_MOVIE)) {
            getLoadAfterComingMovies(params, callback)
        }
        if (typeMovie.containsKey(Constants.KEY_PlAYING_MOVIE)) {
            getLoadAfterPlayingMovies(params, callback)
        }
        if (typeMovie.containsKey(Constants.KEY_TOP_MOVIE)) {
            getLoadAfterTopMovies(params, callback)
        }
    }

    fun getInitialPopularMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.getMovies(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onShowFetched(movies, callback)
                }, {
                    performErrorInitial(params, callback, it)
                })
        )
    }

    fun getInitalGenreMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>, genreId: String) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.getMoviesByGenre(pageNumber, genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onShowFetched(movies, callback)
                }, {
                    performErrorInitial(params, callback, it)
                })
        )
    }

    fun getInitalSearchMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>, key: String) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.searchMovieByName(key, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onShowFetched(movies, callback)
                }, {
                    performErrorInitial(params, callback, it)
                })
        )
    }

    fun getInitalPlayingMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getPlayingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onShowFetched(movies, callback)
                        }, {
                            performErrorInitial(params, callback, it)
                        })
        )
    }

    fun getInitalTopMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getTopMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onShowFetched(movies, callback)
                        }, {
                            performErrorInitial(params, callback, it)
                        })
        )
    }

    fun getInitalComingMovies(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getComingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onShowFetched(movies, callback)
                        }, {
                            performErrorInitial(params, callback, it)
                        })
        )
    }

    fun getLoadAfterPopularMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.getMovies(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onMoreShowFetched(movies, callback)
                }, {
                    performErrorLoadAfter(params, callback, it)
                })
        )
    }

    fun getLoadAfterGenreMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>, genreId: String) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.getMoviesByGenre(pageNumber, genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onMoreShowFetched(movies, callback)
                }, {
                    performErrorLoadAfter(params, callback, it)
                })
        )
    }

    fun getLoadAfterSearchMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>, key: String) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            moviesRepository.searchMovieByName(key, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    onMoreShowFetched(movies, callback)
                }, {
                    performErrorLoadAfter(params, callback, it)
                })
        )
    }

    fun getLoadAfterPlayingMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getPlayingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onMoreShowFetched(movies, callback)
                        }, {
                            performErrorLoadAfter(params, callback, it)
                        })
        )
    }

    fun getLoadAfterTopMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getTopMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onMoreShowFetched(movies, callback)
                        }, {
                            performErrorLoadAfter(params, callback, it)
                        })
        )
    }

    fun getLoadAfterComingMovies(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
                moviesRepository.getComingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            onMoreShowFetched(movies, callback)
                        }, {
                            performErrorLoadAfter(params, callback, it)
                        })
        )
    }

    fun clear() {
        compositeDisposable.clear()
    }
}

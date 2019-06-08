package com.asterisk.tuandao.themoviedb.di

import android.app.Application
import androidx.room.Room
import com.asterisk.tuandao.themoviedb.BuildConfig
import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.local.MovieDatabase
import com.asterisk.tuandao.themoviedb.data.source.local.MoviesLocalDataSource
import com.asterisk.tuandao.themoviedb.data.source.remote.MoviesApi
import com.asterisk.tuandao.themoviedb.data.source.remote.MoviesRemoteDataSource
import com.asterisk.tuandao.themoviedb.util.Constants
import com.asterisk.tuandao.themoviedb.util.hasNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Module
    companion object {
        private const val QUERRY_PARAMETER_API_KEY = "api_key"
        private const val API_KEY = BuildConfig.API_KEY
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val DATABASE_NAME = "movies.db"
        @JvmStatic
        @Singleton
        @Provides
        fun provideInteceptor(application: Application) = Interceptor { chain ->
            var request = chain.request()
            val originalHttpUrl = request.url()
            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter(
                    QUERRY_PARAMETER_API_KEY,
                    API_KEY
                )
                .build()
            request = if (application.hasNetwork()) {
                request.newBuilder()
                    .url(newUrl)
                    .header(
                        "Cache-Control",
                        "public, max-age=${Constants.MAXIMUM_REQUEST_TIMEOUT}"
                    )
                    .build()
            } else {
                request.newBuilder()
                    .url(newUrl)
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=${Constants.MAXIMUM_CACHE_TIME}"
                    )
                    .build()
            }
            chain.proceed(request)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideCache(application: Application) = Cache(application.cacheDir, Constants.CACHE_SIZE)

        @JvmStatic
        @Singleton
        @Provides
        fun provideHttpLog(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache
        ): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(httpLoggingInterceptor)
            }
            return okHttpClient.addInterceptor(interceptor)
                .cache(cache)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): MoviesApi {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideDatabase(context: Application) =
            Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, DATABASE_NAME).build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideMovieDao(database: MovieDatabase) = database.movieDao()

    }

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: MoviesRemoteDataSource): MoviesDataSource.Remote

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(localDataSource: MoviesLocalDataSource): MoviesDataSource.Local
}

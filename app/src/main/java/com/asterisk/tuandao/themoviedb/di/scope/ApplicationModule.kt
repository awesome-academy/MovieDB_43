package com.asterisk.tuandao.themoviedb.di.scope

import android.app.Application
import com.asterisk.tuandao.themoviedb.BuildConfig
import com.asterisk.tuandao.themoviedb.data.source.MoviesDataSource
import com.asterisk.tuandao.themoviedb.data.source.remote.MoviesApi
import com.asterisk.tuandao.themoviedb.data.source.remote.MoviesRemoteDataSource
import com.asterisk.tuandao.themoviedb.di.ViewModelModule
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

@Module(includes = [ViewModelModule::class])
abstract class ApplicationModule {

    @Module
    companion object {
        private const val QUERRY_PARAMETER_API_KEY = "api_key"
        private const val API_KEY = BuildConfig.API_KEY
        private const val BASE_URL = "https://api.themoviedb.org/"

        @JvmStatic
        @Singleton
        @Provides
        fun provideInteceptor(application: Application) = Interceptor { chain ->
            var request = chain.request()
            val originalHttpUrl = request.url()
            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter(QUERRY_PARAMETER_API_KEY, API_KEY)
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
        fun provideLog(): HttpLoggingInterceptor {
            val interceptorLog = HttpLoggingInterceptor()
            interceptorLog.level = HttpLoggingInterceptor.Level.BODY
            return interceptorLog
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: Interceptor
            , httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache
        ): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(httpLoggingInterceptor)
            }
            return okHttpClient.cache(cache)
                .addInterceptor(interceptor)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient) =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoviesApi::class.java)
    }

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: MoviesRemoteDataSource): MoviesDataSource.Remote
}

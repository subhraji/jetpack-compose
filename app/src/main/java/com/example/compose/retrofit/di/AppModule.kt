package com.example.compose.retrofit.di

import com.example.compose.BuildConfig
import com.example.compose.retrofit.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private val mLoggingInterceptor = HttpLoggingInterceptor()

    private fun getHttpLogClientWithToken(): OkHttpClient {
        if (BuildConfig.DEBUG) {
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(mLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService() : ApiService =
        Retrofit
            .Builder()
            .run {
                baseUrl(ApiService.BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(getHttpLogClientWithToken())
                build()
            }.create(ApiService::class.java)


    /*@Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }*/
}
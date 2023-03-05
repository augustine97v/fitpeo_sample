package com.csi_weselychurch.di

import com.fitpeo.sample.ui.home.HomeService
import com.fitpeo.sample.utils.ApplicationSettings
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


/**
 * @author Augustine on 01/03/23.
 * */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {


        var token = ApplicationSettings.read("BearerToken", "")
        Timber.d("TOKEN_CHRIST -->  $token")
        /*  val client = OkHttpClient.Builder()
              .addInterceptor(OAuthInterceptor("Bearer", token))
              .build()*/

        return Retrofit.Builder()
//            .client(client)
            .baseUrl("https://jsonplaceholder.typicode.com/") //live URL
//            .baseUrl("https://creat4es.com/api/") //live URL
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideEventService(retrofit: Retrofit.Builder): HomeService {
        return retrofit
            .build()
            .create(HomeService::class.java)
    }

}

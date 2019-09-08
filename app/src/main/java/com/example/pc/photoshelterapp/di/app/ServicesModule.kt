package com.example.pc.photoshelterapp.di.app

import com.example.pc.photoshelterapp.data.ContactsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient



@Module
class ServicesModule {

    @Provides
    fun getContactsMethods(retrofit: Retrofit): ContactsApiService {
        return retrofit.create(ContactsApiService::class.java)
    }

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level  = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
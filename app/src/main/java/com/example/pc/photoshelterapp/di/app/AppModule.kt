package com.example.pc.photoshelterapp.di.app

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

}
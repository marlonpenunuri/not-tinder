package com.example.pc.photoshelterapp

import com.example.pc.photoshelterapp.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 *Application object and Dagger graph initiation
 **/
class PhotoShelterApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out PhotoShelterApp> {
        return DaggerAppComponent.factory().create(this)
    }


}
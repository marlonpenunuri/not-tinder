package com.example.pc.photoshelterapp

import android.app.Application
import dagger.android.AndroidInjector
import com.example.pc.photoshelterapp.di.app.DaggerAppComponent
import dagger.android.DaggerApplication


class PhotoShelterApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out PhotoShelterApp> {
        return DaggerAppComponent.factory().create(this)
    }


}
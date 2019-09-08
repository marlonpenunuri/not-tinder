package com.example.pc.photoshelterapp.di.app

import android.content.Context
import com.example.pc.photoshelterapp.PhotoShelterApp
import com.example.pc.photoshelterapp.di.vm.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        ServicesModule::class,
        ViewModelModule::class]
) interface AppComponent : AndroidInjector<PhotoShelterApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}


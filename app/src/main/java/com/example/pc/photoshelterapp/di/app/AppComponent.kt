package com.example.pc.photoshelterapp.di.app

import android.content.Context
import com.example.pc.photoshelterapp.QuestsApp
import com.example.pc.photoshelterapp.di.vm.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 *Main component binded to the application itself. Modules provide dependencies for their owner.
 **/

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        ServicesModule::class,
        ViewModelModule::class]
) interface AppComponent : AndroidInjector<QuestsApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}


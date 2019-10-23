package com.nu9ve.quests.di.app

import android.content.Context
import com.nu9ve.quests.QuestsApp
import com.nu9ve.quests.di.vm.ViewModelModule
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


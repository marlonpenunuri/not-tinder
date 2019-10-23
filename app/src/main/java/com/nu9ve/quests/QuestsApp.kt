package com.nu9ve.quests

import com.nu9ve.quests.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 *Application object and Dagger graph initiation
 **/
class QuestsApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out QuestsApp> {
        return DaggerAppComponent.factory().create(this)
    }


}
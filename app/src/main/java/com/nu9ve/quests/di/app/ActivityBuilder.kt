package com.nu9ve.quests.di.app

import com.nu9ve.quests.di.main.MainActivityModule
import com.nu9ve.quests.di.main.MainFragmentModule
import com.nu9ve.quests.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *Constructs activities subcomponents and their respective dependencies
 **/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MainFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity


}
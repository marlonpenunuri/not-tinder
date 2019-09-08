package com.example.pc.photoshelterapp.di.app

import com.example.pc.photoshelterapp.di.main.MainActivityModule
import com.example.pc.photoshelterapp.di.main.MainFragmentModule
import com.example.pc.photoshelterapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MainFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity


}
package com.example.pc.photoshelterapp.di.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pc.photoshelterapp.ui.contacts.ContactsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.example.pc.photoshelterapp.ui.main.MainActivity
import dagger.Binds


@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainActivity {
        return mainActivity
    }

//    @Provides
//    fun provideContactViewModel(mainActivity: MainActivity): ContactsViewModel {
//        return ViewModelProviders.of(this)[MyViewModel::class.java]
//    }

}
package com.example.pc.photoshelterapp.di.main

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.pc.photoshelterapp.ui.contact.ContactViewModel
import com.example.pc.photoshelterapp.ui.main.MainActivity
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    fun provideContactsViewModel(main: MainActivity, viewModelFactory: ViewModelProvider.Factory): ContactViewModel {
        return ViewModelProviders.of(main, viewModelFactory).get(ContactViewModel::class.java)
    }

}
package com.nu9ve.quests.di.main

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nu9ve.quests.ui.contact.ContactViewModel
import com.nu9ve.quests.ui.main.MainActivity
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    fun provideContactsViewModel(main: MainActivity, viewModelFactory: ViewModelProvider.Factory): ContactViewModel {
        return ViewModelProviders.of(main, viewModelFactory).get(ContactViewModel::class.java)
    }

}
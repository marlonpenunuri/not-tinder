package com.example.pc.photoshelterapp.di.main

import com.example.pc.photoshelterapp.ui.contacts.ContactDetailsFragment
import com.example.pc.photoshelterapp.ui.contacts.ContactListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeContactListFragment(): ContactListFragment

    @ContributesAndroidInjector
    abstract fun contributeContactDetailFragment(): ContactDetailsFragment


}
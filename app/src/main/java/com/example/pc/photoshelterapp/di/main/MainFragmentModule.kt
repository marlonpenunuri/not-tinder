package com.example.pc.photoshelterapp.di.main

import com.example.pc.photoshelterapp.ui.contacts.ContactDetailsFragment
import com.example.pc.photoshelterapp.ui.contacts.ContactListFragment
import com.example.pc.photoshelterapp.ui.tutorial.TutorialPageFragment
import com.example.pc.photoshelterapp.ui.tutorial.TutorialParentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeContactListFragment(): ContactListFragment

    @ContributesAndroidInjector
    abstract fun contributeContactDetailFragment(): ContactDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeTutorialParentFragment(): TutorialParentFragment

    @ContributesAndroidInjector
    abstract fun contributeTutorialPageFragment(): TutorialPageFragment

}
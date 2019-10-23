package com.example.pc.photoshelterapp.di.main

import com.example.pc.photoshelterapp.ui.contact.ContactDetailsFragment
import com.example.pc.photoshelterapp.ui.contact.ContactListFragment
import com.example.pc.photoshelterapp.ui.login.LoginFragment
import com.example.pc.photoshelterapp.ui.map.MapFragment
import com.example.pc.photoshelterapp.ui.quests.QuestDetailsFragment
import com.example.pc.photoshelterapp.ui.tutorial.TutorialPageFragment
import com.example.pc.photoshelterapp.ui.tutorial.TutorialParentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeTutorialParentFragment(): TutorialParentFragment

    @ContributesAndroidInjector
    abstract fun contributeTutorialPageFragment(): TutorialPageFragment

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributeContactListFragment(): ContactListFragment

    @ContributesAndroidInjector
    abstract fun contributeContactDetailsFragment(): ContactDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeQuestDetailsFragment(): QuestDetailsFragment

}
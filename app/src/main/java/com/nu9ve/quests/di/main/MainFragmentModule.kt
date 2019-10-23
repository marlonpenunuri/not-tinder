package com.nu9ve.quests.di.main

import com.nu9ve.quests.ui.contact.ContactDetailsFragment
import com.nu9ve.quests.ui.contact.ContactListFragment
import com.nu9ve.quests.ui.login.LoginFragment
import com.nu9ve.quests.ui.map.MapFragment
import com.nu9ve.quests.ui.quests.QuestDetailsFragment
import com.nu9ve.quests.ui.tutorial.TutorialPageFragment
import com.nu9ve.quests.ui.tutorial.TutorialParentFragment
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
package com.example.pc.photoshelterapp.di.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pc.photoshelterapp.ui.contact.ContactViewModel
import com.example.pc.photoshelterapp.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    internal abstract fun bindContactViewModel(contactViewModel: ContactViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}
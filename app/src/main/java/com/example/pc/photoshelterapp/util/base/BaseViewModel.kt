package com.example.pc.photoshelterapp.util.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pc.photoshelterapp.util.Event

/**
 *Facilitates commonly used livedata
 **/

abstract class BaseViewModel : ViewModel() {

    protected val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>>
        get() = _snackbarText

    protected val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    fun setToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }
}
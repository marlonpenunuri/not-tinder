package com.example.pc.photoshelterapp.util.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pc.photoshelterapp.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val job = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    protected val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    protected val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>>
        get() = _snackbarText

    protected val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    protected val _swipeRefreshLoading = MutableLiveData(false)
    val swipeRefreshLoading: LiveData<Boolean>
        get() = _swipeRefreshLoading

    fun setToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }
}
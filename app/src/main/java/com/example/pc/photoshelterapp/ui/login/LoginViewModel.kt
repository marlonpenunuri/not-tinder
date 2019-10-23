package com.example.pc.photoshelterapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pc.photoshelterapp.util.Event
import com.example.pc.photoshelterapp.util.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(): BaseViewModel() {

    private val _correctLoginEvent = MutableLiveData<Event<Boolean>>()
    val correctLoginEvent: LiveData<Event<Boolean>>
        get() = _correctLoginEvent

    fun login(mail: String, pass: String){
        _correctLoginEvent.value = Event(true)

//        if(mail == "erikiado@gmail.com" && pass == "erikerik"){
//            _correctLoginEvent.value = Event(true)
//        } else {
//            _correctLoginEvent.value = Event(false)
//        }
    }

}
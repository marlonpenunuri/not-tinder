package com.nu9ve.quests.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nu9ve.quests.util.Event
import com.nu9ve.quests.util.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(): BaseViewModel() {

    private val _correctLoginEvent = MutableLiveData<Event<Boolean>>()
    val correctLoginEvent: LiveData<Event<Boolean>>
        get() = _correctLoginEvent

    fun login(mail: String, pass: String){
        if(mail == "erikiado@gmail.com" && pass == "erikerik"){
            _correctLoginEvent.value = Event(true)
        } else {
            _correctLoginEvent.value = Event(false)
        }
    }

}
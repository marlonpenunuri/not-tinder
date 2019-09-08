package com.example.pc.photoshelterapp.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc.photoshelterapp.Event
import com.example.pc.photoshelterapp.data.Success
import com.example.pc.photoshelterapp.domain.entities.ContactDetailEntity
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import com.example.pc.photoshelterapp.domain.entities.ContactListEntity
import com.example.pc.photoshelterapp.domain.interactors.ContactsInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val contactsInteractor: ContactsInteractor
) : ViewModel() {


    private val _eventGoToDetail = MutableLiveData<Event<Unit>>()
    val eventGoToDetail: LiveData<Event<Unit>>
        get() = _eventGoToDetail

    private val _contactList = MutableLiveData<List<ContactEntity>>()
    val contactList: LiveData<List<ContactEntity>>
        get() = _contactList

    private val _contactDetail = MutableLiveData<ContactDetailEntity>()
    val contactDetail: LiveData<ContactDetailEntity>
        get() = _contactDetail

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>>
        get() = _snackbarText

    private val _toolbarTitle= MutableLiveData<String>()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle



    fun getContacts() {
        viewModelScope.launch {
            _dataLoading.value = true
            when (val response = contactsInteractor.getContactsList()) {
                is Success<*> -> {
                    val obj = response.data as ContactListEntity
                    obj.run {
                        results.let {
                            _contactList.value = it
                        }
                    }
                }
                is Error -> _snackbarText.value = Event(response.message!!)
            }
            _dataLoading.value = false
        }
    }

    fun getContactDetails() {
        viewModelScope.launch {
            _dataLoading.value = true
            when (val response = contactsInteractor.getContactDetails()) {
                is Success<*> -> {
                    val obj = response.data as ContactDetailEntity
                    obj.run {
                        this.let {
                            _contactDetail.value = it
                        }
                    }
                }
                is Error -> _snackbarText.value = Event(response.message!!)
            }
            _dataLoading.value = false
        }
    }

    fun goToContactDetails(){
        _eventGoToDetail.value = Event(Unit)
    }
}
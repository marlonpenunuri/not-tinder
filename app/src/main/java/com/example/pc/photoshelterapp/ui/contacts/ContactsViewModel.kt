package com.example.pc.photoshelterapp.ui.contacts

import android.widget.Toast
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


    private val _eventGoToDetail = MutableLiveData<Event<ContactEntity>>()
    val eventGoToDetail: LiveData<Event<ContactEntity>>
        get() = _eventGoToDetail

    private val _contactList = MutableLiveData<MutableList<ContactEntity>>()
    val contactList: LiveData<MutableList<ContactEntity>>
        get() = _contactList

    private val _contactListPage = MutableLiveData<Int>().apply{value = 1}
    val contactListPage: LiveData<Int>
        get() = _contactListPage

    private val _contactDetail = MutableLiveData<ContactDetailEntity>()
    val contactDetail: LiveData<ContactDetailEntity>
        get() = _contactDetail

    private val _refreshingList = MutableLiveData<Boolean>()
    val refreshingList: LiveData<Boolean>
        get() = _refreshingList

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
            when (val response = contactsInteractor.getContactsList(_contactListPage.value ?: 1)) {
                is Success<*> -> {
                    val obj = response.data as ContactListEntity
                    obj.run {
                        results.let {
                            val list = _contactList.value ?: mutableListOf()
                            list.addAll(it)
                            _contactList.value = list
                        }
                    }
                }
                is Error -> _snackbarText.value = Event(response.message!!)
            }
            _refreshingList.value = false
        }
    }

    fun removeContact(contact: ContactEntity) {
        val list = _contactList.value ?: mutableListOf()
        list.remove(contact)
       _contactList.value  = list
    }

    fun goToContactDetails(contact: ContactEntity){
        _eventGoToDetail.value = Event(contact)
    }

    fun resetList(){
        _contactList.value = mutableListOf()
        _contactListPage.value = 1
        getContacts()
    }

    fun nextPage(){
        _contactListPage.value = contactListPage.value?.plus(1)
        getContacts()
    }
}
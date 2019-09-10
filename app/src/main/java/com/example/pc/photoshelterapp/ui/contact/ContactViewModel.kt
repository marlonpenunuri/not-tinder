package com.example.pc.photoshelterapp.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pc.photoshelterapp.data.Error
import com.example.pc.photoshelterapp.data.Success
import com.example.pc.photoshelterapp.domain.entity.ContactEntity
import com.example.pc.photoshelterapp.domain.entity.ContactListEntity
import com.example.pc.photoshelterapp.domain.interactor.ContactInteractor
import com.example.pc.photoshelterapp.util.Event
import com.example.pc.photoshelterapp.util.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val contactsInteractor: ContactInteractor
) : BaseViewModel() {


    private val _eventGoToDetail = MutableLiveData<Event<ContactEntity?>>()
    val eventGoToDetail: LiveData<Event<ContactEntity?>>
        get() = _eventGoToDetail

    private val _contactList = MutableLiveData(arrayListOf<ContactEntity>())
    val contactList: LiveData<ArrayList<ContactEntity>>
        get() = _contactList

    private val _likedContacts = MutableLiveData(arrayListOf<String>())
    val likedContacts: LiveData<ArrayList<String>>
        get() = _likedContacts

    private val _contactListPage = MutableLiveData<Int>().apply{value = 1}
    private val contactListPage: LiveData<Int>
        get() = _contactListPage

    private val _refreshingList = MutableLiveData<Boolean>()
    val refreshingList: LiveData<Boolean>
        get() = _refreshingList


    fun getContacts() {
        viewModelScope.launch {
            _refreshingList.value = true
            when (val response = contactsInteractor.getContactsList(_contactListPage.value ?: 1)) {
                is Success<*> -> {
                    val obj = response.data as ContactListEntity
                    obj.run {
                        results.let {
                            _contactList.value = _contactList.value?.apply{addAll(it)}
                        }
                    }
                }
                is Error -> _snackbarText.value = Event(response.errorMessage)
            }
            _refreshingList.value = false
        }
    }

    fun removeContact(contact: ContactEntity) {
        _contactList.value = _contactList.value?.apply { remove(contact) }
    }

    fun goToContactDetails(contact: ContactEntity){
        resetList()
        _eventGoToDetail.value = Event(contact)
    }

    fun addLikedContact(email: String){
        _likedContacts.value = _likedContacts.value?.apply{add(email)}
    }

    fun removeLikedContact(email: String){
        _likedContacts.value = _likedContacts.value?.apply{remove(email)}
    }

    fun resetList(){
        _contactList.value = _contactList.value?.apply { clear() }
        _contactListPage.value = 1
    }

    fun nextPage(){
        _contactListPage.value = contactListPage.value?.plus(1)
        getContacts()
    }
}
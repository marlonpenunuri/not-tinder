package com.example.pc.photoshelterapp.domain.interactors

import com.example.pc.photoshelterapp.data.ContactsRepository
import com.example.pc.photoshelterapp.data.ParseResult
import javax.inject.Inject

class ContactsInteractor @Inject constructor(
    private val contactsRepository: ContactsRepository
) {



    suspend fun getContactsList(): ParseResult {
        return contactsRepository.getContactsList()
    }

    suspend fun getContactDetails(): ParseResult {
        return contactsRepository.getContactDetails()
    }
}
package com.example.pc.photoshelterapp.domain.interactors

import com.example.pc.photoshelterapp.data.ContactsRepository
import com.example.pc.photoshelterapp.data.ParseResult
import javax.inject.Inject

class ContactsInteractor @Inject constructor(
    private val contactsRepository: ContactsRepository
) {

    suspend fun getContactsList(page: Int): ParseResult {
        return contactsRepository.getContactsList(page)
    }

    suspend fun getContactDetails(): ParseResult {
        return contactsRepository.getContactDetails()
    }
}
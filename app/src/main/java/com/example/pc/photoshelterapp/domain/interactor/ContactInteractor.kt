package com.example.pc.photoshelterapp.domain.interactor

import com.example.pc.photoshelterapp.data.ParseResult
import com.example.pc.photoshelterapp.data.repository.ContactsRepository
import javax.inject.Inject

/**
 *Interactors are in chare of usecases and any logic inbetween datasources and presentation
 * layer.
 **/

class ContactInteractor @Inject constructor(
    private val contactsRepository: ContactsRepository
) {

    suspend fun getContactsList(page: Int): ParseResult {
        return contactsRepository.getContactsList(page)
    }

}
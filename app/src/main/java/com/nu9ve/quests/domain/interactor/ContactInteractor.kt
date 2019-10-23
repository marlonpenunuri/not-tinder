package com.nu9ve.quests.domain.interactor

import com.nu9ve.quests.data.ParseResult
import com.nu9ve.quests.data.repository.ContactsRepository
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
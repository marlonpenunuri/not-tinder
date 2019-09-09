package com.example.pc.photoshelterapp.data

import com.example.pc.photoshelterapp.domain.entities.ContactDetailEntity
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import com.example.pc.photoshelterapp.domain.entities.ContactListEntity
import javax.inject.Inject

class ContactsRemoteDataSource @Inject constructor(
    private val contactsApi: ContactsApiService
) : ContactsDataSource {

    override suspend fun getContactsList(page: Int): ParseResult {
        val hola = BaseApiCall().callApi{contactsApi.getContactsList(page = page)}
        return hola
    }

    override suspend fun getContactDetails(): ParseResult {
        val hola = Success(ContactDetailEntity())
        return hola
    }
}
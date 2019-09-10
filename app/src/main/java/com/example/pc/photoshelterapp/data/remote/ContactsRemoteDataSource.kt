package com.example.pc.photoshelterapp.data.remote

import com.example.pc.photoshelterapp.data.BaseApiCall
import com.example.pc.photoshelterapp.data.ParseResult
import com.example.pc.photoshelterapp.data.contracts.ContactsDataSource
import com.example.pc.photoshelterapp.data.service.ContactApiService
import javax.inject.Inject

/**
 *Class in charge of connecting to an external Api to retrieve contacts-related info
 **/

class ContactsRemoteDataSource @Inject constructor(
    private val contactsApi: ContactApiService
) : ContactsDataSource {

    override suspend fun getContactsList(page: Int): ParseResult {
        return BaseApiCall().callApi{contactsApi.getContactsList(page = page)}
    }

}
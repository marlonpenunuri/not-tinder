package com.nu9ve.quests.data.remote

import com.nu9ve.quests.data.BaseApiCall
import com.nu9ve.quests.data.ParseResult
import com.nu9ve.quests.data.contracts.ContactsDataSource
import com.nu9ve.quests.data.service.ContactApiService
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
package com.nu9ve.quests.data.repository

import com.nu9ve.quests.data.ParseResult
import com.nu9ve.quests.data.contracts.ContactsDataSource
import com.nu9ve.quests.data.remote.ContactsRemoteDataSource
import javax.inject.Inject

/**
 *The repository functions as a middleman between the app and datasources, it also determines
 * the source of truth. In these case, no local datasource was needed.
 **/

class ContactsRepository @Inject constructor(
    private val remoteDataSource: ContactsRemoteDataSource
) : ContactsDataSource {


    override suspend fun getContactsList(page: Int): ParseResult {
        return remoteDataSource.getContactsList(page)
    }

}
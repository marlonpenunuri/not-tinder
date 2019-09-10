package com.example.pc.photoshelterapp.data.repository

import com.example.pc.photoshelterapp.data.ParseResult
import com.example.pc.photoshelterapp.data.contracts.ContactsDataSource
import com.example.pc.photoshelterapp.data.remote.ContactsRemoteDataSource
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
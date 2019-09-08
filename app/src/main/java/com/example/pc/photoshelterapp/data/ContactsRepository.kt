package com.example.pc.photoshelterapp.data

import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val remoteDataSource: ContactsRemoteDataSource
) : ContactsDataSource {


    override suspend fun getContactsList():ParseResult  {
        return remoteDataSource.getContactsList()
    }

    override suspend fun getContactDetails():ParseResult  {
        return remoteDataSource.getContactDetails()
    }
}
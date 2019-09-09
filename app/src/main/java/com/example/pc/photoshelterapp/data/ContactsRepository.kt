package com.example.pc.photoshelterapp.data

import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val remoteDataSource: ContactsRemoteDataSource
) : ContactsDataSource {


    override suspend fun getContactsList(page: Int):ParseResult  {
        return remoteDataSource.getContactsList(page)
    }

    override suspend fun getContactDetails():ParseResult  {
        return remoteDataSource.getContactDetails()
    }
}
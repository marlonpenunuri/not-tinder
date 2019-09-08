package com.example.pc.photoshelterapp.data

interface ContactsDataSource {

    suspend fun getContactsList():ParseResult

    suspend fun getContactDetails():ParseResult
}
package com.example.pc.photoshelterapp.data

interface ContactsDataSource {

    suspend fun getContactsList(page: Int):ParseResult

    suspend fun getContactDetails():ParseResult
}
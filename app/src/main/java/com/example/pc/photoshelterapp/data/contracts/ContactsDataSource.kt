package com.example.pc.photoshelterapp.data.contracts

import com.example.pc.photoshelterapp.data.ParseResult

/**
 *Contract to make sure the entire data layer implements the same methods
 **/

interface ContactsDataSource {

    suspend fun getContactsList(page: Int): ParseResult
}
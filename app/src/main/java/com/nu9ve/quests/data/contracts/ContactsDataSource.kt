package com.nu9ve.quests.data.contracts

import com.nu9ve.quests.data.ParseResult

/**
 *Contract to make sure the entire data layer implements the same methods
 **/

interface ContactsDataSource {

    suspend fun getContactsList(page: Int): ParseResult
}
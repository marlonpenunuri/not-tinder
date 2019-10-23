package com.nu9ve.quests.data.service

import com.nu9ve.quests.domain.entity.ContactListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApiService {

    @GET(".")
    suspend fun getContactsList(
        @Query("seed") seed: String = "foobar",
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 25,
        @Query("inc") inc: String = "name,gender,email,picture,cell"
    ): Response<ContactListEntity>
}
package com.example.pc.photoshelterapp.data

import com.example.pc.photoshelterapp.domain.entities.ContactListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsApiService {

    @GET(".")
    suspend fun getContactsList(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 5,
        @Query("inc") inc: String = "name,gender,email,picture"
    ): Response<ContactListEntity>
}
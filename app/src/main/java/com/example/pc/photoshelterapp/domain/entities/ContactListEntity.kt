package com.example.pc.photoshelterapp.domain.entities

import com.google.gson.annotations.SerializedName

data class ContactListEntity (
    val results: List<ContactEntity>,
    val info: InfoEntity
)
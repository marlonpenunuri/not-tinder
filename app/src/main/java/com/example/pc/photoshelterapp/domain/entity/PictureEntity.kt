package com.example.pc.photoshelterapp.domain.entity

import java.io.Serializable

data class PictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
): Serializable
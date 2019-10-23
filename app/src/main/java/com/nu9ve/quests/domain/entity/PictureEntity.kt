package com.nu9ve.quests.domain.entity

import java.io.Serializable

data class PictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
): Serializable
package com.nu9ve.quests.domain.entity

import java.io.Serializable


open class ContactEntity (
    val name: NameEntity,
    val picture: PictureEntity,
    val gender: String,
    val email: String,
    val cell: String
): Serializable
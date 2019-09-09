package com.example.pc.photoshelterapp.domain.entities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.io.Serializable


data class ContactEntity (
    val name: NameEntity,
    val picture: PictureEntity,
    val gender: String,
    val email: String,
    val cell: String
): Serializable
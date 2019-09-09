package com.example.pc.photoshelterapp.domain.entities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


data class ContactEntity (
    val name: NameEntity,
    val picture: PictureEntity,
    val gender: String
)

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(view);
}
package com.example.pc.photoshelterapp.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.ItemContactBinding
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import com.example.pc.photoshelterapp.util.CropCircleTransformation
import com.example.pc.photoshelterapp.util.base.BaseListAdapter
import com.squareup.picasso.Picasso
import java.util.*

class ContactListAdapter(
    private var contactsViewModel: ContactsViewModel,
    private val isEmptyList: ((Boolean) -> Unit)? = null
) : BaseListAdapter<ContactEntity, ContactListAdapter.ContactsViewHolder>(DIFF_UTIL) {


    companion object {
        @JvmField
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactEntity>() {
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(
                oldItem: ContactEntity,
                newItem: ContactEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    val likes = contactsViewModel.likedContacts.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, pos: Int) {
        val item = getItem(pos)
        holder.setData(item)
        if (pos == itemCount - 6) holder.loadNextElements()
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            getCurrentList()
        } else {
            getCurrentList().filter {
                it.name.first.contains(
                    query,
                    ignoreCase = true
                ) || it.name.last.contains(query, ignoreCase = true)
            }
        }
        super.filter(filteredList) { isEmptyList?.invoke(it) }
    }


    override fun submitList(list: List<ContactEntity>?) {
        super.submitList(list) { isEmptyList?.invoke(it) }
    }

    fun sortList(list: List<ContactEntity>): List<ContactEntity> {
        return list.sortedWith(compareByDescending { item -> likes?.find { x -> item.email == x } })
    }

    inner class ContactsViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ContactEntity) {

            with(binding) {
                clickListener = View.OnClickListener {
                    when (it.id) {
                        R.id.userCardLayout -> contactsViewModel.goToContactDetails(item)
                        R.id.likeBtn -> {
                            likes?.find { x -> x == item.email }?.let {
                                liked = false
                                contactsViewModel.removeLikedContact(item.email)
                            } ?: run {
                                liked = true
                                contactsViewModel.addLikedContact(item.email)
                            }

                        }
                    }
                }
                longClickListener = View.OnLongClickListener {
                    contactsViewModel.removeLikedContact(item.email)
                    contactsViewModel.removeContact(item)
                    return@OnLongClickListener true
                }
                contact = item
                liked = !likes?.find { x -> x == item.email }.isNullOrEmpty()
                Picasso.get().load(item.picture.medium).transform(CropCircleTransformation())
                    .into(userImageView)
            }
        }

        fun loadNextElements() {
            contactsViewModel.nextPage()
        }
    }

}
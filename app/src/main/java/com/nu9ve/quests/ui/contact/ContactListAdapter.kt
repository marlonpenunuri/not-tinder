package com.nu9ve.quests.ui.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nu9ve.quests.R
import com.nu9ve.quests.databinding.ItemContactBinding
import com.nu9ve.quests.domain.entity.ContactEntity
import com.nu9ve.quests.util.CropCircleTransformation
import com.nu9ve.quests.util.base.BaseListAdapter
import com.squareup.picasso.Picasso

class ContactListAdapter(
    private var contactViewModel: ContactViewModel,
    private val isEmptyList: ((Boolean) -> Unit)? = null
) : BaseListAdapter<ContactEntity, ContactListAdapter.ContactsViewHolder>(
    DIFF_UTIL
) {


    /**
     *Needed by ListAdapter to compare items and determine if there are changes
     **/
    companion object {
        @JvmField
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactEntity>() {
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    /**
     *Avoided Local Storage because of time and simplicity
     **/
    val likes = contactViewModel.likedContacts.value

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

    /**
     *Sorts list by most recent like before rendering it
     **/
    override fun submitList(list: List<ContactEntity>?) {
        val sortedList = list?.sortedWith(compareByDescending { item -> likes?.find { email -> email == item.email} })
        super.submitList(sortedList) { isEmptyList?.invoke(it) }
    }

    @SuppressLint("DefaultLocale")
    inner class ContactsViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ContactEntity) {

            with(binding) {
                clickListener = View.OnClickListener {
                    when (it.id) {
                        R.id.userCardLayout -> contactViewModel.goToContactDetails(item)
                        R.id.likeBtn -> {
                            likes?.find { email -> email == item.email }?.let {
                                liked = false
                                contactViewModel.removeLikedContact(item.email)
                            } ?: run {
                                liked = true
                                contactViewModel.addLikedContact(item.email)
                            }

                        }
                    }
                }
                longClickListener = View.OnLongClickListener {
                    contactViewModel.removeLikedContact(item.email)
                    contactViewModel.removeContact(item)
                    return@OnLongClickListener true
                }
                fullName = "${item.name.first.capitalize()} ${item.name.last.capitalize()}"
                liked = !likes?.find { email -> email == item.email }.isNullOrEmpty()
                Picasso.get().load(item.picture.medium)
                    .transform(CropCircleTransformation())
                    .placeholder(R.drawable.ic_unknown_user)
                    .into(userImageView)
            }
        }

        fun loadNextElements() {
            contactViewModel.nextPage()
        }
    }

}
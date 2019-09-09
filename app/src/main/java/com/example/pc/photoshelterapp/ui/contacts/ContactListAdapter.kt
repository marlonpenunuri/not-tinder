package com.example.pc.photoshelterapp.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pc.photoshelterapp.databinding.ItemContactBinding
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso

class ContactListAdapter(
    private var contactsViewModel: ContactsViewModel,
    private val isEmptyList: ((Boolean) -> Unit)? = null
) : ListAdapter<ContactEntity, ContactListAdapter.ContactsViewHolder>(DIFF_UTIL) {

    companion object {
        @JvmField
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ContactEntity>() {
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var linksActualPage = JsonObject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, pos: Int) {
        val item = getItem(pos)
        holder.setData(item)
        if (pos == itemCount - 6) holder.loadNextElements(item)
    }

//    fun filter(query: String) {
//        val filteredList = if (query.isEmpty()) {
//            getCurrentList()
//        } else {
//            getCurrentList().filter { it.name.contains(query, ignoreCase = true) }
//        }
//        super.filter(filteredList) { isEmptyList?.invoke(it) }
//    }
//
//    override fun submitList(list: List<ContactEntity>?) {
//        super.submitList(list) { isEmptyList?.invoke(it) }
//    }
//
//    fun setActualPage(links: JsonObject) {
//        linksActualPage = links
//    }

    inner class ContactsViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ContactEntity) {
            with(binding){
                clickListener = View.OnClickListener {
                    contactsViewModel.goToContactDetails(item)
                }
                longClickListener = View.OnLongClickListener {
                    contactsViewModel.removeContact(item)
                    return@OnLongClickListener true
                }
                contact = item
                Picasso.get().load(item.picture.medium).into(userImageView)
            }
        }

        fun loadNextElements(item: ContactEntity) {
            contactsViewModel.nextPage()
        }
    }

}
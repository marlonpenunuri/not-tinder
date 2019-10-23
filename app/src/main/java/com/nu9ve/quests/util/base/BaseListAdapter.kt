package com.nu9ve.quests.util.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 *Generic listadapter providing reusable code for filtering and list manipulation
 **/

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder>(callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(callback) {

    private var list: List<T> = mutableListOf()
    private var filtered: List<T> = mutableListOf()

    fun getCurrentList(): List<T> = list

    fun filter(filterList: List<T>, isEmptyList: (Boolean) -> Unit) {
        filtered = filterList
        isEmptyList(filtered.isEmpty())
        super.submitList(filtered.toList())
    }

    fun submitList(listT: List<T>?, isEmptyList: (Boolean) -> Unit) {
        list = listT!!
        val temp = filtered.toMutableList()
        temp.removeAll { f -> list.find { c -> c == f } == null }
        if (filtered.isEmpty()) {
            isEmptyList(list.isEmpty())
            super.submitList(list.toList())
        } else {
            filtered = temp
            isEmptyList(filtered.isEmpty())
            super.submitList(filtered.toList())
        }
    }

    fun clearFilter() {
        val temp = filtered.toMutableList()
        temp.clear()
        filtered = temp
    }
}
package com.example.galaxyonenative.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.galaxyonenative.presentation.model.TabItem

class TabDiffUtil(
    private val oldList: List<TabItem>,
    private val newList: List<TabItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isActive == newList[newItemPosition].isActive
    }
}
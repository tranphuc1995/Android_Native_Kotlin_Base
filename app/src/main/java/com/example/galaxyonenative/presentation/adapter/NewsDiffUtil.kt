package com.example.galaxyonenative.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.galaxyonenative.presentation.model.FilmUiModel


class NewsDiffUtil(
    private val oldList: List<FilmUiModel>,
    private val newList: List<FilmUiModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].image == newList[newItemPosition].image
    }


}
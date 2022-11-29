package com.example.galaxyonenative.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.galaxyonenative.R
import com.example.galaxyonenative.presentation.model.FilmUiModel

class NewsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listNewsItem = ArrayList<FilmUiModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bindData(listNewsItem[position])
    }

    override fun getItemCount(): Int {
        return listNewsItem.size
    }

    fun updateList(newNewsItemList: List<FilmUiModel>) {
        val diffUtil = NewsDiffUtil(oldList = listNewsItem, newList = newNewsItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listNewsItem.clear()
        for(element in newNewsItemList){
            listNewsItem.add(element.copy())
        }
        diffResult.dispatchUpdatesTo(this)
    }
}
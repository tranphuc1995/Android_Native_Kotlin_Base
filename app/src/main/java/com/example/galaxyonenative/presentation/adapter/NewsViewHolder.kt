package com.example.galaxyonenative.presentation.adapter

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galaxyonenative.presentation.model.FilmUiModel
import kotlinx.android.synthetic.main.news_item.view.*


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(newsItem: FilmUiModel) {
        Glide.with(itemView.context).load(newsItem.image).into(itemView.ivThumbnails)
        itemView.tvTitle.text = newsItem.title

    }
}
package com.example.galaxyonenative.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.galaxyonenative.presentation.model.TabItem
import com.bumptech.glide.Glide
import com.example.galaxyonenative.R
import kotlinx.android.synthetic.main.tab_item.view.*


class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(tabItem: TabItem) {
        Glide.with(itemView.context).load(tabItem.icon).into(itemView.ivIcon)
        itemView.tvTitle.text = tabItem.title
        if (tabItem.isActive) {
            itemView.tvTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            itemView.lnTabContainer.setBackgroundResource(R.drawable.background_tab_active)
        } else {
            itemView.tvTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.neutral200))
            itemView.lnTabContainer.setBackgroundResource(R.drawable.background_tab_not_active)
        }
    }
}
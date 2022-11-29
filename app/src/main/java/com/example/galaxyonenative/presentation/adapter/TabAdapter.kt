package com.example.galaxyonenative.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.galaxyonenative.R
import com.example.galaxyonenative.presentation.model.TabItem

class TabAdapter(private val onclickItem: ((Int) -> Unit)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listTabItem = ArrayList<TabItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.tab_item, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TabViewHolder).bindData(listTabItem[position])
        (holder as TabViewHolder).itemView.setOnClickListener {
            onclickItem.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listTabItem.size
    }

    fun updateList(newListItemTab: List<TabItem>) {
        val diffUtil = TabDiffUtil(oldList = listTabItem, newList = newListItemTab)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listTabItem.clear()
        for(element in newListItemTab){
            listTabItem.add(element.copy())
        }
        diffResult.dispatchUpdatesTo(this)
    }
}


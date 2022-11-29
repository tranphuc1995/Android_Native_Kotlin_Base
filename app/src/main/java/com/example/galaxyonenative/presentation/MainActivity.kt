package com.example.galaxyonenative.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galaxyonenative.presentation.adapter.TabAdapter
import com.example.galaxyonenative.presentation.viewmodel.TabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import androidx.activity.viewModels
import com.example.galaxyonenative.R
import com.example.galaxyonenative.presentation.adapter.NewsAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var tabAdapter: TabAdapter
    private lateinit var newsAdapter: NewsAdapter

    private val tabViewModel: TabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addObservers()
        initView()
        tabViewModel.initDataView()
        tabViewModel.getListFilm()
    }

    private fun initView() {
        tabAdapter = TabAdapter(
            onclickItem = { position -> tabViewModel.clickTab(position = position) })
        rvTab.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvTab.adapter = tabAdapter
        newsAdapter = NewsAdapter()
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = newsAdapter
    }

    private fun addObservers() {
        tabViewModel.getlistTabItem().observe(this, Observer { listTabItems ->
            tabAdapter.updateList(newListItemTab = listTabItems)
        })

        tabViewModel.getListNewsItem().observe(this, Observer { listNewsItems ->
            newsAdapter.updateList(newNewsItemList = listNewsItems)
        })
    }
}
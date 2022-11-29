package com.example.galaxyonenative.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galaxyonenative.R
import com.example.galaxyonenative.data.network.doOnFailure
import com.example.galaxyonenative.data.network.doOnLoading
import com.example.galaxyonenative.data.network.doOnSuccess
import com.example.galaxyonenative.domain.usecase.GetListFilmUsecase
import com.example.galaxyonenative.presentation.model.FilmUiModel
import com.example.galaxyonenative.presentation.model.TabItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(private val getListFilmUsecase: GetListFilmUsecase) :
    ViewModel() {
    private var listTabItems = arrayListOf<TabItem>();
    private var listNewsItems = arrayListOf<FilmUiModel>();
    private val listTabItemLiveData: MutableLiveData<List<TabItem>> = MutableLiveData()

    private val listNewsItemLiveData: MutableLiveData<List<FilmUiModel>> = MutableLiveData()

    fun getlistTabItem(): LiveData<List<TabItem>> {
        return listTabItemLiveData
    }

    fun getListNewsItem(): LiveData<List<FilmUiModel>> {
        return listNewsItemLiveData
    }

    fun getListFilm() {
        viewModelScope.launch {
            getListFilmUsecase.execute()
                .doOnSuccess {
                    listNewsItemLiveData.value = it
                }
                .doOnFailure { }
                .doOnLoading {
                    Log.i("debug_phuc", "xxx")
                }.collect()
        }
    }

    fun initDataView() {
        //data tab
        listTabItems.add(
            TabItem(
                icon = R.drawable.ic_calendar,
                title = "test",
                isActive = true
            )
        )
        listTabItems.add(
            TabItem(
                icon = R.drawable.ic_calendar,
                title = "test1"
            )
        )
        listTabItems.add(
            TabItem(
                icon = R.drawable.ic_calendar,
                title = "test2"
            )
        )
        listTabItemLiveData.postValue(listTabItems)

        //data content
        listNewsItems.add(
            FilmUiModel(
                id = "1",
                title = "asdaasdasdasd",
                "https://vaithuhayho.com/wp-content/uploads/2021/03/hinh-anh-dep-ve-tinh-yeu-9-1.jpg"
            )
        )
        listNewsItems.add(
            FilmUiModel(
                id = "2",
                title = "asdaasdasdasd",
                "https://toanthaydinh.com/wp-content/uploads/2020/04/hinh-anh-buon.png6_.jpg"
            )
        )
        listNewsItemLiveData.postValue(listNewsItems)
    }

    fun clickTab(position: Int) {
        for (i in 0 until listTabItems.size) {
            listTabItems[i].isActive = i == position
        }
        listTabItemLiveData.postValue(listTabItems)
    }

    override fun onCleared() {
        super.onCleared()
        // Dispose All your Subscriptions to avoid memory leaks
    }
}
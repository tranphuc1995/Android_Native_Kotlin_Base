package com.example.galaxyonenative.domain.repository

import com.example.galaxyonenative.data.entities.FilmEntity
import com.example.galaxyonenative.data.network.ResultNetwork
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getListFilm(): Flow<ResultNetwork<FilmEntity>>
}
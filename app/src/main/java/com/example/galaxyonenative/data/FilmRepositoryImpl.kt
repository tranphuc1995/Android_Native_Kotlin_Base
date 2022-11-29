package com.example.galaxyonenative.data


import com.example.galaxyonenative.data.api.FilmApi
import com.example.galaxyonenative.data.entities.FilmEntity
import com.example.galaxyonenative.data.network.BaseRepository
import com.example.galaxyonenative.data.network.ResultNetwork
import com.example.galaxyonenative.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val filmApi: FilmApi) : BaseRepository(),
    FilmRepository {
    override suspend fun getListFilm(): Flow<ResultNetwork<FilmEntity>> {
        return safeApiCall {
            filmApi.getListFilm()
        }
    }
}
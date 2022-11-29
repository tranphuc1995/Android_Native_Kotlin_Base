package com.example.galaxyonenative.data.api


import com.example.galaxyonenative.data.entities.FilmEntity
import retrofit2.Response
import retrofit2.http.GET

interface FilmApi {
    @GET("3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getListFilm(): Response<FilmEntity>
}
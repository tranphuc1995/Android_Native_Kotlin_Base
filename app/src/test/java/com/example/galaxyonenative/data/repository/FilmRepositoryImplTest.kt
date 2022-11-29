package com.example.galaxyonenative.data.repository

import com.example.galaxyonenative.data.FilmRepositoryImpl
import com.example.galaxyonenative.data.api.FilmApi
import com.example.galaxyonenative.data.entities.FilmEntity
import com.example.galaxyonenative.data.network.ResultNetwork
import com.example.galaxyonenative.domain.repository.FilmRepository
import com.example.galaxyonenative.presentation.model.FilmUiModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(MockitoJUnitRunner::class)
class FilmRepositoryImplTest {
    lateinit var filmRepository: FilmRepository
    lateinit var filmApi: FilmApi
    lateinit var filmRepositoryMock: FilmRepository

    @Before
    fun setUp() {
        filmRepositoryMock = mock(FilmRepository::class.java)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi
                        .Builder()
                        .run {
                            add(KotlinJsonAdapterFactory())
                            build()
                        })
            )
            .build()

        filmApi = retrofit.create(FilmApi::class.java)


        filmRepository = FilmRepositoryImpl(filmApi)
    }


    @Test
    fun test_get_list_film_from_api_success() = runBlocking {
        var result = filmApi.getListFilm()
        assertEquals(20, result.body()?.results?.size)
    }

    @Test
    fun test_get_list_film_from_api_success1() = runBlocking {
        var resultAssert =
            flow { emit(ResultNetwork.Success(FilmEntity(page = null, results = null))) }
        Mockito.`when`(filmRepositoryMock.getListFilm()).thenReturn(resultAssert)
        assertEquals(resultAssert, filmRepositoryMock.getListFilm())
    }
}


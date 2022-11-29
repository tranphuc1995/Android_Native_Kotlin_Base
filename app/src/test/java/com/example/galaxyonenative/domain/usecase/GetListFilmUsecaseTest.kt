package com.example.galaxyonenative.domain.usecase

import com.example.galaxyonenative.data.entities.FilmEntity
import com.example.galaxyonenative.data.network.ResultNetwork
import com.example.galaxyonenative.domain.mapper.FilmEntityMapper
import com.example.galaxyonenative.domain.repository.FilmRepository
import com.example.galaxyonenative.presentation.model.FilmUiModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetListFilmUsecaseTest {
    lateinit var filmUsecase: GetListFilmUsecase

    @Before
    fun setUp() {
        filmUsecase = mock(GetListFilmUsecase::class.java)
    }

    @Test
    fun test_get_list_film_usecase() = runBlocking {

        var resultAssert = flow { emit(ResultNetwork.Success(arrayListOf<FilmUiModel>())) }
        Mockito.`when`(filmUsecase.execute()).thenReturn(resultAssert)
        assertEquals(resultAssert, filmUsecase.execute())
    }
}
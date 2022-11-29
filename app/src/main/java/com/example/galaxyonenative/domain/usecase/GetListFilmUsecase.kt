package com.example.galaxyonenative.domain.usecase

import com.example.galaxyonenative.data.network.ResultNetwork
import com.example.galaxyonenative.data.network.map
import com.example.galaxyonenative.domain.mapper.FilmEntityMapper
import com.example.galaxyonenative.domain.repository.FilmRepository
import com.example.galaxyonenative.presentation.model.FilmUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject



interface GetListFilmUsecase {
    suspend fun execute(): Flow<ResultNetwork<List<FilmUiModel>>>
}

class GetListFilmUsecaseImpl @Inject constructor(
    private val filmRepository: FilmRepository,
    private val filmEntityMapper: FilmEntityMapper
) :
    GetListFilmUsecase {
    override suspend fun execute(): Flow<ResultNetwork<List<FilmUiModel>>> {
        return filmRepository.getListFilm()
            .map { result -> result.map { filmEntity -> filmEntityMapper.mapFrom(filmEntity) } }
    }
}
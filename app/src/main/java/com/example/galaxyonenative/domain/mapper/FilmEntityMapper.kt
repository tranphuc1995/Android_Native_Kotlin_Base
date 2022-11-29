package com.example.galaxyonenative.domain.mapper

import com.example.galaxyonenative.data.entities.FilmEntity
import com.example.galaxyonenative.data.mapper.Mapper
import com.example.galaxyonenative.presentation.model.FilmUiModel
import javax.inject.Inject


class FilmEntityMapper @Inject constructor() : Mapper<FilmEntity?, List<FilmUiModel>> {
    override fun mapFrom(from: FilmEntity?): List<FilmUiModel> {
        return from?.results?.map { data ->
            FilmUiModel(
                title = data?.overview ?: "",
                image = "https://image.tmdb.org/t/p/w500/${(data?.poster_path ?: "")}"
            )
        } ?: arrayListOf<FilmUiModel>()
    }
}
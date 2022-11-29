package com.example.galaxyonenative.data.entities

data class FilmEntity(val page: Int?, val results: List<Result?>?) {
    data class Result(val overview: String?, val poster_path: String?) {

    }
}
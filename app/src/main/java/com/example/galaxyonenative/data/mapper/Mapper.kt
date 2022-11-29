package com.example.galaxyonenative.data.mapper

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}

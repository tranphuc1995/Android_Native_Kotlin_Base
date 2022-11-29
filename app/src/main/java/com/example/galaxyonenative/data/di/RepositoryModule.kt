package com.example.galaxyonenative.data.di


import com.example.galaxyonenative.data.FilmRepositoryImpl
import com.example.galaxyonenative.data.api.FilmApi
import com.example.galaxyonenative.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@InstallIn(ActivityRetainedComponent::class)
@Module
class RepositoryModule {


    @ActivityRetainedScoped
    @Provides
    fun provideFilmRepository(quotesApi: FilmApi): FilmRepository {
        return FilmRepositoryImpl(quotesApi)
    }
}
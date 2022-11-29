package com.example.galaxyonenative.presentation.di


import com.example.galaxyonenative.domain.mapper.FilmEntityMapper
import com.example.galaxyonenative.domain.repository.FilmRepository
import com.example.galaxyonenative.domain.usecase.GetListFilmUsecase
import com.example.galaxyonenative.domain.usecase.GetListFilmUsecaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
class UsecaseModule {
    @Provides
    @ActivityRetainedScoped
    fun provideGetListFilmUsecase(
        filmRepository: FilmRepository,
        filmEntityMapper: FilmEntityMapper
    ): GetListFilmUsecase {
        return GetListFilmUsecaseImpl(filmRepository, filmEntityMapper)
    }
}
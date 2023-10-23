package com.mz.profile.di

import com.mz.profile.domain.repository.AlbumsRepository
import com.mz.profile.domain.repository.PhotosRepository
import com.mz.profile.domain.repository.UsersRepository
import com.mz.profile.domain.usecase.GetAlbumsUseCase
import com.mz.profile.domain.usecase.GetPhotosUseCase
import com.mz.profile.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUsersUseCase(usersRepository: UsersRepository): GetUsersUseCase {
        return GetUsersUseCase(usersRepository)
    }
    @Provides
    @Singleton
    fun provideGetAlbumsUseCase(albumsRepository: AlbumsRepository): GetAlbumsUseCase {
        return GetAlbumsUseCase(albumsRepository)
    }
    @Provides
    @Singleton
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotosUseCase {
        return GetPhotosUseCase(photosRepository)
    }
}
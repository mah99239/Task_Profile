package com.mz.profile.data.di

import com.mz.profile.data.remote.ApiService
import com.mz.profile.data.repoitory.AlbumsRepositoryImpl
import com.mz.profile.data.repoitory.PhotosRepositoryImpl
import com.mz.profile.data.repoitory.UsersRepositoryImpl
import com.mz.profile.domain.repository.AlbumsRepository
import com.mz.profile.domain.repository.PhotosRepository
import com.mz.profile.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository
    @Binds
    fun bindPhotosRepository(photosRepositoryImpl: PhotosRepositoryImpl): PhotosRepository
    @Binds
    fun bindAlbumsRepository(albumsRepositoryImpl: AlbumsRepositoryImpl): AlbumsRepository
    
    
 /*    @Provides
    @Singleton
    fun provideUsersRepositoryImpl(
        apiService: ApiService,
    ): UsersRepository {
        return UsersRepositoryImpl(apiService)
    } */

/*     @Provides
    @Singleton
    fun providePhotosRepositoryImpl(
        apiService: ApiService,
    ): PhotosRepository {
        return PhotosRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideAlbumsRepositoryImpl(
        apiService: ApiService,
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(apiService)
    } */
}
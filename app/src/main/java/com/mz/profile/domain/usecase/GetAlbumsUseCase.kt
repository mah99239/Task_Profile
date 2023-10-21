package com.mz.profile.domain.usecase

import com.mz.profile.domain.repository.AlbumsRepository

class GetAlbumsUseCase(private val albumsRepository: AlbumsRepository) {
     operator fun invoke(userId: Int) = albumsRepository.getAlbums(userId)
}
package com.mz.profile.domain.usecase

import com.mz.profile.domain.repository.PhotosRepository

class GetPhotosUseCase(private val photosRepository: PhotosRepository) {
     operator fun invoke(albumId: Int) = photosRepository.getPhotos(albumId)
}
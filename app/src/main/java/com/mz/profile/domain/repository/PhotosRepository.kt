package com.mz.profile.domain.repository

import com.mz.profile.domain.model.Photo
import com.mz.profile.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


 fun interface PhotosRepository {

    fun getPhotos(albumId: Int): Flow<Resource<List<Photo>>>

}
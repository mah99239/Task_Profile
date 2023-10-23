package com.mz.profile.domain.repository

import com.mz.profile.domain.utils.Resource
import com.mz.profile.domain.model.Album
import kotlinx.coroutines.flow.Flow


fun interface AlbumsRepository {
    fun getAlbums(userId: Int): Flow<Resource<List<Album>>>

}
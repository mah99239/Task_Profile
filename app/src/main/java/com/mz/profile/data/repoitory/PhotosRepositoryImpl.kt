package com.mz.profile.data.repoitory

import com.mz.profile.data.remote.ApiService
import com.mz.profile.domain.model.Photo
import com.mz.profile.domain.repository.PhotosRepository
import com.mz.profile.domain.utils.AppDispatchers
import com.mz.profile.domain.utils.Dispatcher
import com.mz.profile.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(private val apiService: ApiService,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher) : PhotosRepository {
    override fun getPhotos(albumId: Int): Flow<Resource<List<Photo>>> = flow {

        emit(Resource.Loading())
        try {

            Timber.e("currentThread: ${currentCoroutineContext()}")

            val data = apiService.getPhotos(albumId).await()
            if (data.isEmpty()) {
                emit(Resource.Empty())
            } else {
                emit(Resource.Success(data))
            }
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }.flowOn(ioDispatcher)


}
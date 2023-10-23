package com.mz.profile.data.repoitory


import com.mz.profile.data.remote.ApiService
import com.mz.profile.domain.model.Album
import com.mz.profile.domain.repository.AlbumsRepository
import com.mz.profile.domain.utils.AppDispatchers
import com.mz.profile.domain.utils.Dispatcher
import com.mz.profile.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class AlbumsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher) : AlbumsRepository {
    
    override fun getAlbums(userId: Int): Flow<Resource<List<Album>>> = flow {
        emit(Resource.Loading())
        try {

            Timber.e("currentThread: ${currentCoroutineContext()}")

            val data = apiService.getAlbums(userId)
            if (data.isEmpty()) {
                emit(Resource.Empty())
            } else {
                emit(Resource.Success(data))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }.flowOn(ioDispatcher)
}
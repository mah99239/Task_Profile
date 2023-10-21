package com.mz.profile.data.repoitory

import com.mz.profile.data.remote.ApiService
import com.mz.profile.domain.model.Album
import com.mz.profile.domain.model.User
import com.mz.profile.domain.repository.UsersRepository
import com.mz.profile.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


class UsersRepositoryImpl(private val apiService: ApiService) : UsersRepository {



    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        try {

            Timber.e("currentThread: ${currentCoroutineContext()}")

            val data = apiService.getUsers().await()
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
    }.flowOn(Dispatchers.IO)


}
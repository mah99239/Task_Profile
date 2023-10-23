package com.mz.profile.domain.repository

import com.mz.profile.domain.utils.Resource
import com.mz.profile.domain.model.User
import kotlinx.coroutines.flow.Flow


fun interface UsersRepository {

     fun getUsers(): Flow<Resource<List<User>>>

}
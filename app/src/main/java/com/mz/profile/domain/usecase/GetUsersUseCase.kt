package com.mz.profile.domain.usecase

import com.mz.profile.domain.repository.UsersRepository

class GetUsersUseCase(private val userRepository: UsersRepository) {
     operator fun invoke() = userRepository.getUsers()

}
package com.mz.profile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mz.profile.domain.utils.Resource
import com.mz.profile.domain.model.Album
import com.mz.profile.domain.model.User
import com.mz.profile.domain.usecase.GetAlbumsUseCase
import com.mz.profile.domain.usecase.GetUsersUseCase
import com.mz.profile.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase, private val getAlbumsUseCase: GetAlbumsUseCase
) : BaseViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> get() = _user

    private val _userId: MutableLiveData<Int?> = MutableLiveData()


    private val _albums: MutableLiveData<List<Album>> = MutableLiveData()
    val albums get() = _albums



  

     fun getUsers() = viewModelScope.launch {
        showProgress()

        getUsersUseCase.invoke().collect { usersResponse ->
            when (usersResponse) {
                    is Resource.Loading -> {
                        showProgress()
                        Timber.e("getUsers:Loading")

                    }

                    is Resource.Success -> {
                        Timber.e("getUsers:Success")

                        _users.value = usersResponse.data
                       getRandomUser()
                    }

                    is Resource.Error -> {
                        hideProgress()
                        Timber.e("getUsers:Error: ${usersResponse.exception}")

                    }

                    is Resource.Empty -> {
                        hideProgress()
                        Timber.e("getUsers:Empty")

                    }
            }
        }


    }
   
    fun getRandomUser()
   {
      val userRandom = _users.value?.random()
      _user.value = userRandom !!
      _userId.value = user.value?.id
      getAlbums()
      
   }
   
   private fun getAlbums() {


        viewModelScope.launch {
            getAlbumsUseCase.invoke(_userId.value ?: -1).collect { albumsResponse ->
                when (albumsResponse) {
                    is Resource.Loading -> {
                        Timber.e("getAlbums:Loading")
                    }

                    is Resource.Success -> {
                        _albums.value = albumsResponse.data
                        Timber.e("getAlbums:Success")
                        hideProgress()

                    }

                    is Resource.Error -> {
                        hideProgress()
                        Timber.e("getAlbums:Error: ${albumsResponse.exception}")

                    }

                    is Resource.Empty -> {
                        hideProgress()
                        Timber.e("getAlbums:Empty")

                    }
                }
            }
        }

    }

}
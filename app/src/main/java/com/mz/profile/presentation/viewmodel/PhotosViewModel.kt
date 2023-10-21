package com.mz.profile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mz.profile.domain.model.Photo
import com.mz.profile.domain.usecase.GetPhotosUseCase
import com.mz.profile.domain.utils.Resource
import com.mz.profile.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class PhotosViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :BaseViewModel(){
    private val _albumId : MutableLiveData<Int> = MutableLiveData()

    private val _photos : MutableLiveData<List<Photo>> = MutableLiveData()
    private val _photosSearch : MutableLiveData<List<Photo>?> = MutableLiveData()

    val photos:LiveData<List<Photo>?> get() = _photosSearch

    private val _imageSelected: MutableLiveData<Photo> = MutableLiveData()
    val imageSelected : LiveData<Photo> get() = _imageSelected

    private val _imageUrl : MutableLiveData<String> = MutableLiveData()
    val imageUrl : LiveData<String> get() = _imageUrl

    private fun getPhotos() = viewModelScope.launch {
        showProgress()
        _albumId.value?.let {
            getPhotosUseCase.invoke(it).collect{photosResource->
                when(photosResource){
                    is Resource.Loading -> {
                        showProgress()
                        Timber.e("getPhotos:Loading")
                        Timber.e("getPhotos:Loading $coroutineContext")

                    }

                    is Resource.Success -> {
                        Timber.e("getPhotos:Success")

                        _photos.value = photosResource.data
                        _photosSearch.value = photosResource.data

                    }

                    is Resource.Error -> {
                        hideProgress()
                        Timber.e("getPhotos:Error: ${photosResource.exception}")

                    }

                    is Resource.Empty -> {
                        hideProgress()
                        Timber.e("getPhotos:Empty")

                    }
                }

            }
        }
    }


    fun getAlbumId(id: Int){
        _albumId.value = id
        getPhotos()
    }

    fun getImageTitle(title: String){
      val filterImage =   _photos.value?.filter { photo-> (photo.title.contains(title)) }
        if(filterImage?.isEmpty() == false) {
            _photosSearch.value = filterImage
        }
    }

    fun setImageSelected(photo: Photo)
    {
        _imageSelected.value = photo
        _imageUrl.value = photo.url
        Timber.e("ImageUrl ${photo.url}")
    }

}
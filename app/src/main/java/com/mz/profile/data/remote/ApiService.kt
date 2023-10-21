package com.mz.profile.data.remote


import com.mz.profile.domain.model.Album
import com.mz.profile.domain.model.Photo
import com.mz.profile.domain.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.coroutines.Continuation

interface ApiService {

    @GET("users")
    fun getUsers(): Deferred<List<User>>

    @GET("albums")
    suspend fun getAlbums(@Query("userId") userId: Int): List<Album>


    @GET("photos")
    fun getPhotos(@Query("albumId") albumId: Int): Deferred<List<Photo>>
}

 const val BASE_URL = "https://jsonplaceholder.typicode.com/"

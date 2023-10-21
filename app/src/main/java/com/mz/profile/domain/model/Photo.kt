package com.mz.profile.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String

)
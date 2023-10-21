package com.mz.profile.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Album(
    val userId: Int,
    val id: Int,
    val title: String,

)
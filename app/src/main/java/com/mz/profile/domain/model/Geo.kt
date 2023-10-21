package com.mz.profile.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Geo(
    val lat: String,
    val lng: String
)
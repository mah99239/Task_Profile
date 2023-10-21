package com.mz.profile.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)
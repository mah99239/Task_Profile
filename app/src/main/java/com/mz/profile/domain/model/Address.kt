package com.mz.profile.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
){
    fun getAddressWithStreetCity(): String = "$street, $suite, $city"
}
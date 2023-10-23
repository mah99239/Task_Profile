package com.mz.profile.domain.model


data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String,
                  )
{
   fun getAddressWithStreetCity(): String = "$street, $suite, $city"
}
package com.example.composelazycolumn.model

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val username: String,
    val address: Address
)

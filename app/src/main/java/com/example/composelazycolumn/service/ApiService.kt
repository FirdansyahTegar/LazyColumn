package com.example.composelazycolumn.service

import com.example.composelazycolumn.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
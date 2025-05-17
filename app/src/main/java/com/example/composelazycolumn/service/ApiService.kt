package com.example.composelazycolumn.service

import com.example.composelazycolumn.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("get_users.php")
    suspend fun getUsers(): List<User>
}


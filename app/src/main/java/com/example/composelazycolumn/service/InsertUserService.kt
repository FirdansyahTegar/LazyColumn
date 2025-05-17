package com.example.composelazycolumn.service

import com.example.composelazycolumn.model.InsertUserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InsertUserService {
    @FormUrlEncoded
    @POST("add_users.php")
    suspend fun insertUser(
        @Field("username") username: String,
        @Field("private_key") privateKey: String,
        @Field("email") email: String
    ): Response<InsertUserResponse>
}

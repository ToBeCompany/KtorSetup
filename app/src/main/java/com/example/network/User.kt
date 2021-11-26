package com.example.network

import com.example.network.data.LoginRequest
import com.example.network.data.LoginResponse
import com.example.network.data.RegistrationRequest
import com.example.network.data.RegistrationResponse
import retrofit2.Call
import retrofit2.http.*

//
interface User {
    @POST("/v1/user/Create")
    @Headers("Authorization: Bearer YjcxZWNlZTAtOTAxNi00NDU4LWE1OTEtZjQ4ZjI5ZDYwMzE0")
    fun register(
        @Body userData : RegistrationRequest
    ):Call<RegistrationResponse>

    @POST("/v1/user/Login")
    @Headers("Authorization: Bearer YjcxZWNlZTAtOTAxNi00NDU4LWE1OTEtZjQ4ZjI5ZDYwMzE0")
    fun login(
        @Body userData : LoginRequest
    ):Call<LoginResponse>
}
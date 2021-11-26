package com.example.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Database {
    @Headers("Authorization: Bearer MDNmMjBmNmQtYzBmOS00ZWQzLTlhNDgtOTVhZjVkNmE3YjUz")
    @POST("/v1/db/Create")
    fun createAbobus(@Body request : CreateRowRequest): Call<CreateResponse>
}
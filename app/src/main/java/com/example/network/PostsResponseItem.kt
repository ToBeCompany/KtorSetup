package com.example.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponseItem(
    @SerialName("body")
    val body: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("title")
    val title: String? = "",
    @SerialName("userId")
    val userId: Int? = 0
)
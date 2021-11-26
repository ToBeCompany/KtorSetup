package com.example.network

data class CreateRowRequest(
    val record : Abobus,
    val table : String = "abobuses"
)

data class Abobus(val abobaRating : Int)

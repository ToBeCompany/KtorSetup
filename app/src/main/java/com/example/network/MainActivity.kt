package com.example.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.network.data.LoginRequest
import com.example.network.data.LoginResponse
import com.example.network.data.RegistrationRequest
import com.example.network.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.m3o.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val reference = retrofit.create(User::class.java)
    val referenceDB = retrofit.create(Database::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registration.setOnClickListener {
            val abobus = Abobus(binding.login.text.toString().toInt())
            val requestAbobaCreate = CreateRowRequest(abobus)
            MainScope().launch(Dispatchers.IO) {
                referenceDB.createAbobus(requestAbobaCreate).execute()
            }
        }
    }
}
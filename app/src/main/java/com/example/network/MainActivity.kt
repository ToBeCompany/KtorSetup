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

    val stateA : MutableStateFlow<Resource<LoginResponse>?> = MutableStateFlow(null)

    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.m3o.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val reference = retrofit.create(User::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MainScope().launch {
            stateA.collect {
                when (it){
                    is Resource.Error -> {
                        Toast.makeText(this@MainActivity,
                        it.message,
                        Toast.LENGTH_LONG).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                    }
                }
            }
        }

        findViewById<Button>(R.id.registration).setOnClickListener {
            stateA.value = Resource.Loading()
            MainScope().launch(Dispatchers.IO) {
                val userData = LoginRequest(
                    binding.login.text.toString(),
                    binding.password.text.toString(),
                )
                val result = reference.login(userData).execute()
                if (result.isSuccessful){
                    stateA.value = Resource.Success(result.body()!!)
                }else{
                    stateA.value = Resource.Error(result.code().toString())
                }
            }
        }

    }
}
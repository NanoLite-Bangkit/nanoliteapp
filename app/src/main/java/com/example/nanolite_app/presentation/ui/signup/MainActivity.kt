package com.example.nanolite_app.presentation.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import com.example.nanolite_app.R
import com.example.nanolite_app.databinding.ActivityMainBinding
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.presentation.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

//NanoLite - App

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(isValid(name, email, password)){
                val user = User(name, email, password)
                signUpViewModel.setUserSignUp(user)
                Intent(this, HomeActivity::class.java).let {
                    startActivity(it)
                }
            }
        }


    }

    private fun isValid(name: String,  email: String, password: String): Boolean{
        return if(name.isEmpty()){
            binding.etName.error = "Masukkan nama anda"
            false
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Email tidak valid"
            false
        } else if(password.isEmpty()){
            binding.etPassword.error = "Masukkan password"
            false
        }  else {
            true
        }
    }
}
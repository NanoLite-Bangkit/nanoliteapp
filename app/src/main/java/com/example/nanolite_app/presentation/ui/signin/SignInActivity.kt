package com.example.nanolite_app.presentation.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nanolite_app.databinding.ActivitySigninBinding
import com.example.nanolite_app.presentation.ui.HomeActivity
import com.example.nanolite_app.presentation.ui.signup.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(isValid(email, password)){
                lifecycleScope.launch {
                    viewModel.signIn(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                            toHomeActivity(true)
                        } else {
                            toast()
                        }
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            Intent(this, MainActivity::class.java).let {
                startActivity(it)
            }
        }

    }

    private fun toHomeActivity(success: Boolean){
        if(success){
            Intent(this, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .let {
                    startActivity(it)
                }
        }
    }

    private fun toast(){
        Toast.makeText(this@SignInActivity, "Email atau Password anda salah", Toast.LENGTH_SHORT).show()
    }

    private fun isValid(email: String, password: String): Boolean{
        return if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
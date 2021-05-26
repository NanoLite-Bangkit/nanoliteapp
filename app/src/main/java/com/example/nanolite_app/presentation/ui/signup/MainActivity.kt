package com.example.nanolite_app.presentation.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nanolite_app.R
import com.example.nanolite_app.databinding.ActivityMainBinding
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.presentation.ui.HomeActivity
import com.example.nanolite_app.presentation.ui.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//NanoLite - App

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private var cUser: FirebaseUser? = null
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cUser = signUpViewModel.getCurrentUser()

        if(cUser != null){
            toHomeActivity()
        }

        textWatcher()

        binding.btnSignup.setOnClickListener(this)
        binding.tvSignin.setOnClickListener(this)
    }

    private fun textWatcher() {
        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tiEmail.error = null
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.etName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tiName.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.etPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tiPassword.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun isValid(name: String,  email: String, password: String): Boolean{
        return if(name.isEmpty()){
            binding.tiName.error = "Masukkan nama anda"
            false
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tiEmail.error = "Email tidak valid"
            false
        } else if(password.isEmpty()){
            binding.tiPassword.error = "Masukkan password"
            false
        } else if(password.length < 6) {
            binding.tiPassword.error = "Minimal 6 character"
            false
        } else {
            true
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_signup -> {
                val name = binding.etName.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                if(isValid(name, email, password)){
                    val user = User(name, email, password)

                    lifecycleScope.launch {
                        signUpViewModel.setUserSignUp(user)
                            .addOnSuccessListener {
                                signUpViewModel.insertUserData(name, email)
                                toHomeActivity()
                            }
                            .addOnFailureListener {
                                binding.tiEmail.error = "Email sudah digunakan"
                                Log.d("SignUp", "Failed")
                            }
                    }
                }
            }

            R.id.tv_signin -> {
                Intent(this, SignInActivity::class.java).let {
                    startActivity(it)
                }
            }
        }
    }

    private fun toHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }



}
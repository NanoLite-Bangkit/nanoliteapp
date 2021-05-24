package com.example.nanolite_app.presentation.ui.signin

import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.usecase.AuthenticationUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase): ViewModel(){

    suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return authenticationUseCase.signIn(email, password)
    }
}
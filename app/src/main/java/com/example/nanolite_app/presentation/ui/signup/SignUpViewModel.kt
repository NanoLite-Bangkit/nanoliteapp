package com.example.nanolite_app.presentation.ui.signup

import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.usecase.AuthenticationUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase): ViewModel() {

    suspend fun setUserSignUp(user: User): Task<AuthResult> = authenticationUseCase.setUserAuthentication(user)


}
package com.example.nanolite_app.presentation.ui.signup

import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase): ViewModel() {

    fun setUserSignUp(user: User) = authenticationUseCase.setUserAuthentication(user)
}
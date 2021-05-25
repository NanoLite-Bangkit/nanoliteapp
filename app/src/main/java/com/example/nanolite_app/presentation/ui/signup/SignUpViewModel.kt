package com.example.nanolite_app.presentation.ui.signup

import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val nanoLiteUseCase: NanoLiteUseCase): ViewModel() {

    suspend fun setUserSignUp(user: User): Task<AuthResult> = nanoLiteUseCase.setUserAuthentication(user)

    fun insertUserData(name: String, email: String): Unit = nanoLiteUseCase.insertUserData(name, email)

    fun getCurrentUser(): FirebaseUser? = nanoLiteUseCase.getCurrentUser()

    fun getUserData(email: String): DocumentReference = nanoLiteUseCase.getUserData(email)
}
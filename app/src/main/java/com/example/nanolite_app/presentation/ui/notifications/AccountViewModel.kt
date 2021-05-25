package com.example.nanolite_app.presentation.ui.notifications

import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val nanoLiteUseCase: NanoLiteUseCase): ViewModel() {

    suspend fun signOut(){
        nanoLiteUseCase.signOut()
    }

    fun getUserData(email: String): DocumentReference {
        return nanoLiteUseCase.getUserData(email)
    }

    fun getCurrentUser(): FirebaseUser?{
        return nanoLiteUseCase.getCurrentUser()
    }

}
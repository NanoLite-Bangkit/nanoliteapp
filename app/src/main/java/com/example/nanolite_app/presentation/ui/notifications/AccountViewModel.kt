package com.example.nanolite_app.presentation.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nanolite_app.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase): ViewModel() {

    val signOut = authenticationUseCase.signOut()

}
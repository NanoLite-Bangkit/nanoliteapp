package com.example.nanolite_app.presentation.ui.home

import androidx.lifecycle.*
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import com.example.nanolite_app.utils.DataMapper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val nanoLiteUseCase: NanoLiteUseCase) : ViewModel() {

    fun getHistory(email: String): LiveData<List<Waste>> =
        nanoLiteUseCase.getScanningHistory(email).asLiveData().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    fun getCurrentUser(): FirebaseUser? = nanoLiteUseCase.getCurrentUser()
}
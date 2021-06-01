package com.example.nanolite_app.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import com.example.nanolite_app.utils.DataMapper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassificationViewModel @Inject constructor(private val nanoLiteUseCase: NanoLiteUseCase) : ViewModel() {

    suspend fun insertScanningResult(waste: Waste){
        val scanningEntity = DataMapper.mapDomainToEntites(waste)
        nanoLiteUseCase.insertScanningResult(scanningEntity)
    }

    fun getCurrentUser(): FirebaseUser? = nanoLiteUseCase.getCurrentUser()
}
package com.example.nanolite_app.presentation.ui.detail

import androidx.lifecycle.*
import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import com.example.nanolite_app.utils.DataDummy
import com.example.nanolite_app.utils.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailWasteViewModel @Inject  constructor(private val nanoLiteUseCase: NanoLiteUseCase): ViewModel() {

    fun getScanningResult(date: String): LiveData<List<Waste>> =
            nanoLiteUseCase.getScanningResult(date).asLiveData().map {
                DataMapper.mapEntitiesToDomain(it)
            }


    fun getRecommendation(name: String): ArrayList<Recycler>{
        val list = ArrayList<Recycler>()
        for(rekomen in DataDummy.getRecommendation()){
            if(rekomen.jenisSampah == name){
                list.add(rekomen)
            }
        }
        return list
    }
}
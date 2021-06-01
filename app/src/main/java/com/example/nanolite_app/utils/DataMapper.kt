package com.example.nanolite_app.utils

import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.domain.model.Waste

object DataMapper {

    fun mapDomainToEntites(waste: Waste): ScanningEntity{
        return ScanningEntity(
                null,
                waste.email,
                waste.trashName,
                waste.date,
                waste.imageUri,
                waste.classification,
        )
    }

    fun mapEntitiesToDomain(input: List<ScanningEntity>): List<Waste> =
        input.map { entity ->
            Waste(null,
                    entity.email,
                    entity.trashName,
                    entity.date,
                    entity.imageUri,
                    entity.classification
            )
        }
}
package com.example.domain.converters

import com.example.data.remote.models.ThemeApi
import com.example.data.storage.model.ThemeDto
import com.example.domain.models.Theme

class ThemeConverterImpl {
    fun fromApiToUI(model: ThemeApi): Theme {
        return Theme(id_theme = model.id_theme, name_theme = model.name_theme, vote_for = model.vote_for, vote_against = model.vote_against)
    }

    fun fromApiToDB(model: ThemeApi): ThemeDto {
        return ThemeDto(id_theme = model.id_theme, name_theme = model.name_theme, vote_for = model.vote_for, vote_against = model.vote_against)
    }
}
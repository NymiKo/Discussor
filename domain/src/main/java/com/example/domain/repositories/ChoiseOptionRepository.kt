package com.example.domain.repositories

import com.example.data.remote.models.ChoiseOptionApi
import kotlinx.coroutines.Deferred

interface ChoiseOptionRepository {
    suspend fun choiseOfOption(idTheme: Int, vote_for: String, vote_against: String): Deferred<ChoiseOptionApi>
}
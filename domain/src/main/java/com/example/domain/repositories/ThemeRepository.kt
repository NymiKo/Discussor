package com.example.domain.repositories

import com.example.domain.models.Theme
import kotlinx.coroutines.Deferred

interface ThemeRepository {
    suspend fun fetchTheme(): Deferred<List<Theme>>
}
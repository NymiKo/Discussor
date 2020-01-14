package com.example.data.remote.services

import com.example.data.remote.models.ThemeApi
import retrofit2.http.*
import kotlinx.coroutines.Deferred

interface ThemeService {
    @GET("/show_themes.php")
    fun getTheme(): Deferred<List<ThemeApi>>
}
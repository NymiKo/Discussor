package com.example.data.remote.providers

import com.example.data.remote.helpers.RetrofitFactory
import com.example.data.remote.models.ThemeApi
import com.example.data.remote.services.ThemeService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class ThemeProviderImpl {
    @UnstableDefault
    fun getThemesList(): Deferred<List<ThemeApi>>{
        return RetrofitFactory.getRetrofitClient().create(ThemeService::class.java).getTheme()
    }
}
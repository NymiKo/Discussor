package com.example.domain.repositories.implementations

import com.example.data.remote.providers.ThemeProviderImpl
import com.example.data.storage.RoomDatabaseApp
import com.example.domain.converters.ThemeConverterImpl
import com.example.domain.models.Theme
import com.example.domain.repositories.ThemeRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class ThemeRepositoryImpl(val roomDatabaseApp: RoomDatabaseApp): ThemeRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun fetchTheme(): Deferred<List<Theme>> {
        val providerImpl = ThemeProviderImpl()
        val themeConverter = ThemeConverterImpl()

        try {
            val themes = providerImpl.getThemesList().await()

            return GlobalScope.async {
                themes.map {
                    roomDatabaseApp.themeDao().insertTheme(themeConverter.fromApiToDB(model = it))
                    themeConverter.fromApiToUI(model = it)
                }
            }
        } catch (e: Exception) {
            return GlobalScope.async { error(e) }
        }
    }

}
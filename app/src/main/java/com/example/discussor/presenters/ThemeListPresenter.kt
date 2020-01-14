package com.example.discussor.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.data.storage.RoomDatabaseApp
import com.example.discussor.view.ThemeListView
import com.example.domain.repositories.implementations.ThemeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@InjectViewState
class ThemeListPresenter: MvpPresenter<ThemeListView>() {

    fun fetchThemes(roomDatabaseApp: RoomDatabaseApp) {
        val themeRepositoryImpl = ThemeRepositoryImpl(roomDatabaseApp = roomDatabaseApp)

        viewState.presentLoading()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val themes = themeRepositoryImpl.fetchTheme().await()
                withContext(Dispatchers.Main) {
                    viewState.presentThemes(data = themes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
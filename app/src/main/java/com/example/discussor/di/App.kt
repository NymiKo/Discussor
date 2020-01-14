package com.example.discussor.di

import android.app.Application
import com.example.data.storage.RoomDatabaseApp

class App: Application() {

    companion object {
        lateinit var roomDatabaseApp: RoomDatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabaseApp = RoomDatabaseApp.buildDataSource(context = applicationContext)
    }
}
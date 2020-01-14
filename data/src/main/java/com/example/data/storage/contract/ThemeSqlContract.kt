package com.example.data.storage.contract

class ThemeSqlContract {

    companion object {
        const val fetch = "SELECT * FROM ${RoomContract.tableThemes}"
    }
}
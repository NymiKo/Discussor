package com.example.data.storage.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.data.storage.contract.RoomContract

@Entity(tableName = RoomContract.tableThemes)
data class ThemeDto(@PrimaryKey val id_theme: Int, val name_theme: String, val vote_for: String, val vote_against: String)
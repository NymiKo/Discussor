package com.example.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.data.storage.contract.ThemeSqlContract
import com.example.data.storage.model.ThemeDto

@Dao
interface ThemeDao {

    @Query(ThemeSqlContract.fetch)
    fun fetchThemes(): List<ThemeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTheme(themeDto: ThemeDto)
}
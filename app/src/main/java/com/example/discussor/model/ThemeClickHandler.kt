package com.example.discussor.model

import com.example.domain.models.Theme

interface ThemeClickHandler {
    fun onItemClick(item: Theme, id_theme: Int, textNameTheme: String, voteFor: String, voteAgainst: String)
}
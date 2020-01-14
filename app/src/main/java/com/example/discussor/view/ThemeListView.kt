package com.example.discussor.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.domain.models.Theme

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ThemeListView: MvpView {
    fun presentThemes(data: List<Theme>)
    fun presentLoading()
    fun presentError()
}
package com.example.discussor.view

import com.arellomobile.mvp.MvpView

interface MainActivityView: MvpView {
    fun setId_onThemeListFragment(token: String?)
}
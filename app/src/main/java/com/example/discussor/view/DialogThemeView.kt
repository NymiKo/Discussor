package com.example.discussor.view

import com.arellomobile.mvp.MvpView

interface DialogThemeView: MvpView {
    fun clickableSelectButton()
    fun notClickableVoteFor()
    fun notClickableVoteAgainst()
    fun notClickableSelectButton()
    fun goToCommunicationFragment()
    fun closeDialog()
}
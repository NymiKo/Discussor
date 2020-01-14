package com.example.discussor.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.discussor.view.DialogThemeView
import com.example.domain.repositories.implementations.ChoiseOptionRepositoryImpl
import kotlinx.coroutines.*
import java.lang.Exception

@InjectViewState
class DialogThemePresenter: MvpPresenter<DialogThemeView>() {

    fun checkSelectButton(voteFor: String, voteAgainst: String) {
        if (voteFor == "-" && voteAgainst == "-") {
            viewState.clickableSelectButton()
        }
        if (voteFor != "-" && voteAgainst == "-") {
            viewState.notClickableVoteFor()
        }
        if (voteFor == "-" && voteAgainst != "-") {
            viewState.notClickableVoteAgainst()
        }
        if (voteFor != "-" && voteAgainst != "-") {
            viewState.notClickableSelectButton()
        }
    }

    fun choiseOption(idTheme: Int, vote_for: String, vote_against: String) {
        val choiseOptionRepositoryImpl = ChoiseOptionRepositoryImpl()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                choiseOptionRepositoryImpl.choiseOfOption(
                    idTheme = idTheme, vote_for = vote_for, vote_against = vote_against).await()
                withContext(Dispatchers.Main){
                    viewState.goToCommunicationFragment()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
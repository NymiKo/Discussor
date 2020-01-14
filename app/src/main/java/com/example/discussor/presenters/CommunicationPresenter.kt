package com.example.discussor.presenters

import com.arellomobile.mvp.MvpPresenter
import com.example.discussor.view.CommunicationView
import com.example.domain.repositories.implementations.ChoiseOptionRepositoryImpl
import com.example.domain.repositories.implementations.MessageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CommunicationPresenter: MvpPresenter<CommunicationView>() {

    fun sendMessage(fromWhom: String, toWhom: String, message: String) {
        val messageRepositoryImpl = MessageRepositoryImpl()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                messageRepositoryImpl.sendMessage(fromWhom = fromWhom, toWhom = toWhom, message = message).await()
                withContext(Dispatchers.Main) {
                    viewState.updateText()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
package com.example.discussor.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.example.discussor.view.MainActivityView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivityPresenter: MvpPresenter<MainActivityView>() {

    @SuppressLint("StringFormatInvalid")
    fun getToken(): String {
        var token: String? = "null"

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Ошибка!", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                token = task.result?.token

                // Log and toast
                Log.e("Токен", token)
            })
        return token!!
    }
}
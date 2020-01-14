package com.example.discussor.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.discussor.OpenBottomSheetDialog
import com.example.discussor.R
import com.example.discussor.fcm_services.MyFirebaseMessagingService
import com.example.discussor.presenters.CommunicationPresenter
import com.example.discussor.view.CommunicationView
import kotlinx.android.synthetic.main.activity_communication.*

class CommunicationActivity : MvpAppCompatActivity(), CommunicationView {

    @InjectPresenter
    lateinit var communicationPresenter: CommunicationPresenter

    var getMessage = MyFirebaseMessagingService().getMessage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        val token = "duvY5lT2q1k:APA91bFoznrBgDMcJrARGBPC_tH4SVeewZ9bR5nrBWWHcit7tHwEghZfDjDrJabja9fCIjKojTez0r7BeF61dLNalLhRTJ8Idmx5PqfsGyxCMLxpc4TjmQ1CLxjJnZnWFX-GKfb5qTkO"

        sendMessage.setOnClickListener {
            communicationPresenter.sendMessage(fromWhom = "token", toWhom = token, message = editMessage.text.toString())
        }

        //getMessage = MyFirebaseMessagingService().getMessage()
        Log.e("Message", getMessage)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val openBottomSheetDialog = OpenBottomSheetDialog()
        openBottomSheetDialog.show(supportFragmentManager, "openBottomSheetDialog")
    }

    @SuppressLint("SetTextI18n")
    override fun updateText() {
        textMessage.text = "Success!"
    }
}

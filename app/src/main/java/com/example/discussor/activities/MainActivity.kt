package com.example.discussor.activities

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.discussor.R
import com.example.discussor.presenters.MainActivityPresenter
import com.example.discussor.view.MainActivityView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e("Ошибка!", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.e("Токен", token)
                setId_onThemeListFragment(token = token)
            })
        //setId_onThemeListFragment(mainActivityPresenter.getToken())
    }

    override fun setId_onThemeListFragment(token: String?){
        val navController = this.findNavController(R.id.navHostMain)
        val infoUser = Bundle()
        infoUser.putString("token", token)
        navController.navigate(R.id.themeListFragment, infoUser)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        this.finish()
    }
}

package com.example.discussor.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.discussor.R
import com.example.discussor.adapters.ThemeAdapter
import com.example.discussor.di.App
import com.example.discussor.model.ThemeClickHandler
import com.example.discussor.presenters.DialogThemePresenter
import com.example.discussor.presenters.ThemeListPresenter
import com.example.discussor.view.DialogThemeView
import com.example.discussor.view.ThemeListView
import com.example.domain.models.Theme
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_theme_list.*

class ThemeListFragment: MvpAppCompatFragment(), ThemeListView, DialogThemeView{

    private val mAdapter = ThemeAdapter()

    @InjectPresenter
    lateinit var themePresenter: ThemeListPresenter

    @InjectPresenter
    lateinit var dialogThemePresenter: DialogThemePresenter

    // global variable
    var idTheme: Int = 0
    var vote_for: String = ""
    var vote_against: String = ""
    var token: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //MobileAds.initialize(this.activity, "ca-app-pub-9598699328338820~2423603693")
//        val adRequest = AdRequest.Builder()
//            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//            .build()
//        adViewBanner.loadAd(adRequest)

        mAdapter.attachClickHandler(object : ThemeClickHandler{
            override fun onItemClick(item: Theme, id_theme: Int, textNameTheme: String, voteFor: String, voteAgainst: String) {
                textNameThemeOnDialogSelect.text = textNameTheme
                Log.e("ID", id_theme.toString())
                idTheme = id_theme
                vote_for = voteFor
                vote_against = voteAgainst
                dialogThemePresenter.checkSelectButton(voteFor = vote_for, voteAgainst = vote_against)
                flDialogShow.visibility = View.VISIBLE
                recyclerThemeList.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_theme_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //MobileAds.initialize(this.activity, "ca-app-pub-9598699328338820~2423603693")
        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()
        adViewBanner.loadAd(adRequest)
        setAdapter()

        themePresenter.fetchThemes(roomDatabaseApp = App.roomDatabaseApp)

        Log.e("Токен", token)
        token = arguments?.getString("token")

        closeDialog()
        btnVoteFor.setOnClickListener {
            dialogThemePresenter.choiseOption(idTheme = idTheme, vote_for = token!!, vote_against = "null")
        }
        btnVoteAgainst.setOnClickListener {
            dialogThemePresenter.choiseOption(idTheme = idTheme, vote_for = "null", vote_against = token!!)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId) {
//            R.id. -> {
//                val intent = Intent(this.activity, CommunicationActivity::class.java)
//                //intent.putExtra("id", id)
//                startActivity(intent)
//                return true
//            }
//        }
//        return NavigationUI.onNavDestinationSelected(item!!, Navigation.findNavController(view!!))
//                || super.onOptionsItemSelected(item)
//    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerThemeList.layoutManager = layoutManager
        recyclerThemeList.adapter = mAdapter
    }

    override fun presentThemes(data: List<Theme>) {
        recyclerThemeList.visibility = View.VISIBLE
        pBarLoading.visibility = View.GONE
        textError.visibility = View.GONE

        mAdapter.setData(newTheme = data)
    }

    override fun presentLoading() {
        recyclerThemeList.visibility = View.GONE
        pBarLoading.visibility = View.VISIBLE
        textError.visibility = View.GONE
    }

    override fun presentError() {
        textError.visibility = View.VISIBLE
    }

    override fun clickableSelectButton() {
        btnVoteFor.setBackgroundResource(R.color.clickableVoteFor)
        btnVoteAgainst.setBackgroundResource(R.color.clickableVoteAgainst)
    }

    override fun notClickableVoteFor() {
        btnVoteFor.setBackgroundResource(R.color.notClickableVoteFor)
        btnVoteAgainst.setBackgroundResource(R.color.clickableVoteAgainst)
        btnVoteFor.isClickable = false
    }

    override fun notClickableVoteAgainst() {
        btnVoteAgainst.setBackgroundResource(R.color.notClickableVoteAgainst)
        btnVoteFor.setBackgroundResource(R.color.clickableVoteFor)
        btnVoteAgainst.isClickable = false
    }

    override fun notClickableSelectButton() {
        btnVoteFor.setBackgroundResource(R.color.notClickableVoteFor)
        btnVoteAgainst.setBackgroundResource(R.color.notClickableVoteAgainst)
    }

    override fun goToCommunicationFragment() {
       findNavController().navigate(R.id.communicationActivity)
    }

    override fun closeDialog() {
        btnCloseDialog.setOnClickListener {
            flDialogShow.visibility = View.GONE
            recyclerThemeList.visibility = View.VISIBLE
        }
    }
}
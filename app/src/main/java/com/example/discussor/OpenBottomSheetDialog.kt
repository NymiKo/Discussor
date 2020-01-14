package com.example.discussor

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.discussor.activities.MainActivity
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

class OpenBottomSheetDialog: BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnExit.setOnClickListener {
            val intent = Intent(this.activity, MainActivity::class.java)
            //intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    @Suppress("UNREACHABLE_CODE")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }
}
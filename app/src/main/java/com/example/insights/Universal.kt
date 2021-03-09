package com.example.insights

import android.app.Dialog
import android.content.Context

class Universal {
    private lateinit var mProgressDialog: Dialog
    fun showProgressCircular(context: Context){
        mProgressDialog = Dialog(context)
        mProgressDialog.setContentView(R.layout.progress_bar)
        mProgressDialog.show()
    }
}
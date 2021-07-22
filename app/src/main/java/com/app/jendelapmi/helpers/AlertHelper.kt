package com.app.jendelapmi.helpers

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object AlertHelper {
    fun createAlert( context: Context, title: String, message: String){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok", { dialogInterface, which ->  })
            .show()
    }
}
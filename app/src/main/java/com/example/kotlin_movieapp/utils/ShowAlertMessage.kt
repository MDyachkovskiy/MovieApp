package com.example.kotlin_movieapp.utils

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.FragmentActivity

fun View.showAlertMessagePhoneCall(
    activity: FragmentActivity?
) {
    context.let {
        AlertDialog.Builder(it)
            .setTitle("Доступ к звонкам")
            .setMessage("Для осуществления звонков необходимо предоставить доступ")
            .setPositiveButton("Предоставить доступ") { _, _ ->
                requestCallPhonePermission(activity)
            }
            .setNegativeButton("Не надо") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}

fun View.showAlertMessageContacts(
    activity: FragmentActivity?
) {
    context?.let {
        AlertDialog.Builder(it)
            .setTitle("Доступ к контактам")
            .setMessage("Для отображения контактов необходимо предоставить доступ")
            .setPositiveButton("Предоставить доступ") { _, _ ->
                requestContactsPermission(activity)
            }
            .setNegativeButton("Не надо") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
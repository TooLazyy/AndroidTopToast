package ru.galt.toastlibrary.data

import android.view.View

data class ToastViewSettings(var bgColor: Int, var textColor: Int,
                             var layoutId: Int,
                             var text: String, var useCustomViewId: Boolean,
                             var customView: View? = null)
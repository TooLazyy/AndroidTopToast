package ru.galt.toastlibrary.data

import android.view.View
import ru.galt.toastlibrary.builders.TOAST_TYPE

data class ToastViewParams(var type: TOAST_TYPE,
                           var duration: Long,
                           var bgColor: Int, var textColor: Int,
                           var layoutId: Int,
                           var text: String,
                           var customView: View? = null)
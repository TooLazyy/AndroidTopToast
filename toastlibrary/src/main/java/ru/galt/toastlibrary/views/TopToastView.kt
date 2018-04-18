package ru.galt.toastlibrary.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import ru.galt.toastlibrary.R
import ru.galt.toastlibrary.data.ToastViewSettings
import ru.galt.toastlibrary.extentions.bind

class TopToastView
@JvmOverloads constructor(context: Context, settings: ToastViewSettings) : RelativeLayout(context) {

    private val tvText by bind<TextView>(R.id.tvText)
    private val root by bind<View>(R.id.root)
    private var mToastViewSettings: ToastViewSettings = settings


    init {
        init(context)
    }

    private fun init(context: Context) {
        // if no custom View, inflate by layout id
        if (mToastViewSettings.customView == null) {
            View.inflate(context, mToastViewSettings.layoutId, this)
            //if default toast view, set text and color
            if (mToastViewSettings.useCustomViewId.not()) {
                tvText.text = mToastViewSettings.text
                tvText.setTextColor(mToastViewSettings.textColor)
                root.setBackgroundColor(mToastViewSettings.bgColor)
            }
        } else {
            //else add view
            addView(mToastViewSettings.customView, 0, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

    //if ToastViewSettings.useCustomView is true
    //setText gonna be ignored
    fun setText(text: String) {
        if (mToastViewSettings.useCustomViewId.not()) {
            mToastViewSettings.text = text
            tvText.text = mToastViewSettings.text
        }
    }


    //if ToastViewSettings.useCustomView is true
    //setBgColor gonna be ignored
    fun setBgColor(color: Int) {
        if (mToastViewSettings.useCustomViewId.not()) {
            mToastViewSettings.bgColor = color
            root.setBackgroundColor(mToastViewSettings.bgColor)
        }
    }


    //if ToastViewSettings.useCustomView is true
    //setTextColor gonna be ignored
    fun setTextColor(color: Int) {
        if (mToastViewSettings.useCustomViewId.not()) {
            mToastViewSettings.textColor = color
            tvText.setTextColor(mToastViewSettings.textColor)
        }
    }
}
package ru.galt.toastlibrary.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import ru.galt.toastlibrary.R
import ru.galt.toastlibrary.TopToast
import ru.galt.toastlibrary.builders.TOAST_TYPE
import ru.galt.toastlibrary.data.ToastViewParams
import ru.galt.toastlibrary.extentions.bind

class TopToastView
@JvmOverloads constructor(context: Context, settings: ToastViewParams) : RelativeLayout(context) {

    private val tvText by bind<TextView>(R.id.tvText)
    private val root by bind<View>(R.id.root)
    private var mToastViewSettings: ToastViewParams = settings

    init {
        init(context)
    }

    private fun init(context: Context) {
        // if no custom View, inflate by layout id
        when(mToastViewSettings.type) {
            //default layout id
            TOAST_TYPE.DEFAULT -> initDefaultView()
            //custom layout id
            TOAST_TYPE.CUSTOM_VIEW_ID -> initCustomLayoutId()
            //custom view
            TOAST_TYPE.CUSTOM_VIEW -> initCustomView()
        }
    }

    private fun inflateCustomLayoutId() {
        View.inflate(context, mToastViewSettings.layoutId, this)
    }

    private fun initCustomLayoutId() {
        inflateCustomLayoutId()
    }

    private fun initCustomView() {
        addView(mToastViewSettings.customView, 0, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun initDefaultView() {
        inflateCustomLayoutId()
        tvText.text = mToastViewSettings.text
        tvText.setTextColor(mToastViewSettings.textColor)
        root.setBackgroundColor(mToastViewSettings.bgColor)
    }

    //if ToastViewSettings.useCustomView is true
    //setText gonna be ignored
    fun setText(text: String) {
        if (mToastViewSettings.type == TOAST_TYPE.DEFAULT) {
            mToastViewSettings.text = text
            tvText.text = mToastViewSettings.text
        }
    }


    //if ToastViewSettings.useCustomView is true
    //setBgColor gonna be ignored
    fun setBgColor(color: Int) {
        if (mToastViewSettings.type == TOAST_TYPE.DEFAULT) {
            mToastViewSettings.bgColor = color
            root.setBackgroundColor(mToastViewSettings.bgColor)
        }
    }


    //if ToastViewSettings.useCustomView is true
    //setTextColor gonna be ignored
    fun setTextColor(color: Int) {
        if (mToastViewSettings.type == TOAST_TYPE.DEFAULT) {
            mToastViewSettings.textColor = color
            tvText.setTextColor(mToastViewSettings.textColor)
        }
    }
}
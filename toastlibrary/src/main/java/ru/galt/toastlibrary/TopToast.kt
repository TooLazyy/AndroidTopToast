package ru.galt.toastlibrary

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import ru.galt.toastlibrary.data.ToastViewSettings
import ru.galt.toastlibrary.views.TopToastView
import java.lang.ref.WeakReference

class TopToast(
        viewSettings: ToastViewSettings,
        duration: Long,
        context: Context,
        aboveStatusBar: Boolean,
        gravity: TOASTGRAVITY,
        swipe: TOASTSWIPE) {

    companion object {
        //default view layout id
        val defaultViewId = R.layout.toptoastlib_layout_toast_view
        const val NO_AUTO_REMOVE = -1L
    }


    //weak reference to view
    var mToastView: WeakReference<TopToastView>? = null
        private set
    //on screen duration
    var mDuration = duration
    private val mContext = context
    var mAboveStatusBar = aboveStatusBar
    //top or borrom gravity
    var mGravity: TOASTGRAVITY = gravity
    //swipe to remove direction
    var mToastSwipe: TOASTSWIPE = swipe
    //view settings
    var mViewSettings: ToastViewSettings = viewSettings

    private var mLayoutParams: WindowManager.LayoutParams? = null
    private var mSwipeListener = OnSwipeListener(context).apply {
        swipeRight = {
            if (mToastSwipe == TOASTSWIPE.RIGHT ||
                    mToastSwipe == TOASTSWIPE.ANY) {
                removeTopToast()
            }
        }
        swipeLeft = {
            if (mToastSwipe == TOASTSWIPE.LEFT ||
                    mToastSwipe == TOASTSWIPE.ANY) {
                removeTopToast()
            }
        }
        swipeTop = {
            if (mToastSwipe == TOASTSWIPE.TOP ||
                    mToastSwipe == TOASTSWIPE.ANY) {
                removeTopToast()
            }
        }
        swipeBottom = {
            if (mToastSwipe == TOASTSWIPE.BOTTOM ||
                    mToastSwipe == TOASTSWIPE.ANY) {
                removeTopToast()
            }
        }
    }

    private var mRemoveRunnable = { removeTopToast() }
    private val mHandler = Handler(Looper.getMainLooper())

    init {
        init()
    }

    private fun init() {
        if (mViewSettings.useCustomViewId.not()) {
            mViewSettings.layoutId = defaultViewId
        }
    }

    fun showTopToast() {
        //init view first
        if (mToastView?.get() == null) {
            createView()
        }

        //set swipe listener
        if (mToastSwipe != TOASTSWIPE.NONE) {
            mToastView?.get()?.setOnTouchListener(mSwipeListener)
        }

        //init params first
        createLayoutParams()
/*        if (mLayoutParams == null) {
            createLayoutParams()
        }*/

        //remove prev added view
        try {
            if (mToastView?.get()?.parent != null) {
                getWindowManager().removeViewImmediate(mToastView?.get())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        //add toast new
        try {
            getWindowManager().addView(mToastView?.get(), mLayoutParams)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        //if default toast view, set text and bg color
        if (mViewSettings.useCustomViewId.not()) {
            mToastView?.get()?.setBgColor(mViewSettings.bgColor)
            mToastView?.get()?.setText(mViewSettings.text)
        }

        if (mDuration != NO_AUTO_REMOVE) {
            startRemoveTimer()
        }
    }

    //remove toast view
    fun removeTopToast() {
        Log.d("MIINE", "remove")
        mToastView?.get()?.parent?.let {
            try {
                getWindowManager().removeViewImmediate(mToastView?.get())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun startRemoveTimer() {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed(mRemoveRunnable, mDuration)
    }

    private fun createLayoutParams() {
        mLayoutParams = WindowManager.LayoutParams()

        mLayoutParams!!.gravity = mGravity.gravityValue or Gravity.START

        mLayoutParams!!.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mAboveStatusBar) {
                mLayoutParams!!.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
            } else {
                mLayoutParams!!.type = WindowManager.LayoutParams.TYPE_APPLICATION
            }
        } else {
            mLayoutParams!!.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
            if (mAboveStatusBar) {
                mLayoutParams!!.token = (mContext as Activity).window.decorView.windowToken
                mLayoutParams!!.flags = mLayoutParams!!.flags or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            }
        }

        mLayoutParams!!.width = WindowManager.LayoutParams.MATCH_PARENT
        mLayoutParams!!.height = WindowManager.LayoutParams.WRAP_CONTENT
    }

    private fun createView() {
        val view = TopToastView(mContext, mViewSettings)
        mToastView = WeakReference(view)
    }

    private fun getWindowManager() = (mContext as Activity).windowManager

    enum class TOASTGRAVITY(value: Int) {
        TOP(Gravity.TOP),
        BOTTOM(Gravity.BOTTOM);

        val gravityValue = value
    }

    enum class TOASTSWIPE() {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        //any swipe gonna trigger
        ANY,
        //do not remove on swipe
        NONE
    }
}
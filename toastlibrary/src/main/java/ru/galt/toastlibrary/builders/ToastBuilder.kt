package ru.galt.toastlibrary.builders

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.annotation.LayoutRes
import android.view.View
import ru.galt.toastlibrary.TopToast
import ru.galt.toastlibrary.TopToast.Companion.defaultViewId
import ru.galt.toastlibrary.data.ToastViewSettings
import java.lang.ref.WeakReference

@DslMarker
annotation class ToastDSL

@ToastDSL
class ToastSettingsBuilder(tColor: Int, bgColor: Int, text: String, @LayoutRes viewId: Int,
                           view: View?) {

    //default toast text color
    var textColor: Int = tColor
    //default toast bg color
    var backgroundColor: Int = bgColor
    //text to be shown
    var textToShow = text
    //default toast layout resource
    //if set non default value, @textToShow will be ignored
    @LayoutRes
    var layoutId: Int = viewId
    //toast custom View
    //if set @layoutId gonna be ignored
    var customView: View? = view

    fun build(): ToastViewSettings {
        return ToastViewSettings(backgroundColor, textColor, layoutId,
                textToShow, isCustomView(), customView)
    }

    private fun isCustomView() = (layoutId != defaultViewId) or (customView  != null)
}

@ToastDSL
class ToastBuilder {
    //toast gravity: TOP or BOTTOM
    var toastGravity: TopToast.TOASTGRAVITY = TopToast.TOASTGRAVITY.TOP
    //context (should be an activity class)
    var context: Context? = null
    //should toast to be shown above status bar or not
    var showAboveStatusBar = true
    //toast duration for auto remove in ms
    //SET to TopToast.NO_AUTO_REMOVE if wanna disable auto remove
    var duration = 3000L
    //set remove on wipe direction
    var removeOnSwipe = TopToast.TOASTSWIPE.NONE
    private var viewSettings: ToastViewSettings? = null

    fun viewSettings(textColor: Int = Color.WHITE, backgroundColor: Int = Color.RED,
                     text: String = "text", viewId: Int = defaultViewId,
                     view: View? = null,
                     setup: ToastSettingsBuilder.() -> Unit = {}) {
        val settingsBuilder = ToastSettingsBuilder(textColor, backgroundColor, text, viewId, view)
        settingsBuilder.setup()
        viewSettings = settingsBuilder.build()
    }

    fun build(): TopToast {
        if (context == null) {
            throw IllegalArgumentException("Context cannot be null")
        }
        if (context !is Activity) {
            throw IllegalArgumentException("Context should be activity class")
        }
        if (viewSettings == null) {
            viewSettings()
        }
        return TopToast(
                viewSettings!!,
                duration,
                context!!,
                showAboveStatusBar,
                toastGravity,
                removeOnSwipe
        )
    }

    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR,
            message = "Toasts can't be nested.")
    fun toast(param: () -> Unit = {}) {
    }
}

@ToastDSL
fun topToast(setup: ToastBuilder.() -> Unit): TopToast {
    val toastBuilder = ToastBuilder()
    toastBuilder.setup()
    return toastBuilder.build()
}
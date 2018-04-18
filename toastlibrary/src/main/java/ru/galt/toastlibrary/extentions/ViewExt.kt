package ru.galt.toastlibrary.extentions

import android.support.annotation.IdRes
import android.view.View

fun <T : View?> View.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafelazy { findViewById<T>(idRes) as T }
}

private fun <T> unsafelazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

package ru.galt.toastlibrary

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class OnSwipeListener constructor(context: Context, swipeThreshold: Int = 100) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(context, GestureListener(swipeThreshold))

    var swipeRight: () -> Unit = { }
    var swipeLeft: () -> Unit = { }
    var swipeTop: () -> Unit = { }
    var swipeBottom: () -> Unit = { }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private fun onSwipeRight() {
        swipeRight.invoke()
    }

    private fun onSwipeLeft() {
        swipeLeft.invoke()
    }

    private fun onSwipeTop() {
        swipeTop.invoke()
    }

    private fun onSwipeBottom() {
        swipeBottom.invoke()
    }

    private inner class GestureListener(swipeThreshold: Int) : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = swipeThreshold
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2!!.y - e1!!.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeLeft()
                        } else {
                            onSwipeRight()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (ignored: Exception) {}

            return result
        }
    }
}
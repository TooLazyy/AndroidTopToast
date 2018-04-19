package ru.galt.toptoast

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import ru.galt.toastlibrary.TopToast
import ru.galt.toastlibrary.builders.topToast

class MainActivity : AppCompatActivity() {

    private lateinit var toast: TopToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            showToastWithAutoRemove()
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            showToastWithAutoRemoveOrSwipe()
        }
        findViewById<Button>(R.id.btn3).setOnClickListener {
            showToastWithAutoRemoveCustomColorsAndText()
        }
        findViewById<Button>(R.id.btn4).setOnClickListener {
            showToastWithAutoRemoveCustomColorsAndTextBottom()
        }
        findViewById<Button>(R.id.btn5).setOnClickListener {
            showToastWithSwipeToRemoveOnly()
        }
        findViewById<Button>(R.id.btn6).setOnClickListener {
            showToastWithSwipeToRemoveOnlyAbove()
        }
        findViewById<Button>(R.id.btn7).setOnClickListener {
            showToastWithCustomLayout()
        }
        findViewById<Button>(R.id.btn8).setOnClickListener {
            showToastWithCustomLayoutId()
        }
    }

    /**
     * show TOP toast with auto remove
     * and default layout
     */
    private fun showToastWithAutoRemove() {
        topToast {
            duration = 2000L
            context = this@MainActivity
            showAboveStatusBar = false
        }.showTopToast()
    }

    /**
     * show TOP toast with auto remove or swipe
     * and default layout
     */
    private fun showToastWithAutoRemoveOrSwipe() {
        topToast {
            duration = 2000L
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
        }.showTopToast()
    }

    /**
     * show TOP toast with auto remove, default layout
     * and custom text and colors
     */
    private fun showToastWithAutoRemoveCustomColorsAndText() {
        topToast {
            duration = 2000L
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            viewSettings {
                textToShow = "showToastWithAutoRemoveCustomColorsAndText"
                textColor = Color.WHITE
                backgroundColor = Color.BLACK
            }
        }.showTopToast()
    }

    /**
     * show BOTTOM toast with auto remove, default layout
     * and custom text and colors
     */
    private fun showToastWithAutoRemoveCustomColorsAndTextBottom() {
        topToast {
            duration = 2000L
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            toastGravity = TopToast.TOASTGRAVITY.BOTTOM
            viewSettings {
                textToShow = "showToastWithAutoRemoveCustomColorsAndText"
                textColor = Color.WHITE
                backgroundColor = Color.BLACK
            }
        }.showTopToast()
    }

    /**
     * show TOP toast with auto remove, default layout
     * and custom text and colors
     */
    private fun showToastWithSwipeToRemoveOnly() {
        topToast {
            duration = TopToast.NO_AUTO_REMOVE
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            viewSettings {
                textToShow = "showToastWithAutoRemoveCustomColorsAndText"
                textColor = Color.WHITE
                backgroundColor = Color.BLACK
            }
        }.showTopToast()
    }

    /**
     * show TOP toast with auto remove, default layout
     * and custom text and colors and above status bar
     */
    private fun showToastWithSwipeToRemoveOnlyAbove() {
        topToast {
            duration = TopToast.NO_AUTO_REMOVE
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                textToShow = "showToastWithAutoRemoveCustomColorsAndText"
                textColor = Color.WHITE
                backgroundColor = Color.BLACK
            }
        }.showTopToast()
    }


    /**
     * show TOP toast with auto remove, swipe to remove and custom layout
     * use @customView if you need to setup you toast before show
     */
    private fun showToastWithCustomLayout() {
        val custom = layoutInflater.inflate(R.layout.custom_toast, null)
        val toast = topToast {
            duration = 3000
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                customView = custom
            }
        }
        toast.showTopToast()

        custom.findViewById<Button>(R.id.btn).setOnClickListener {
            toast.removeTopToast()
        }
    }

    /**
     * show TOP toast with auto remove, swipe to remove and custom layoutId
     * use @layoutId if you do not need to setup you toast before show
     */
    private fun showToastWithCustomLayoutId() {
        val toast = topToast {
            duration = 3000
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                layoutId = R.layout.custom_toast2
            }
        }
        toast.showTopToast()
    }

}

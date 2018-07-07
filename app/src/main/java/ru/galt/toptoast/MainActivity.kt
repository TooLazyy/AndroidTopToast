package ru.galt.toptoast

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.galt.toastlibrary.TopToast
import ru.galt.toastlibrary.builders.TOAST_TYPE
import ru.galt.toastlibrary.builders.topToast

class MainActivity : AppCompatActivity() {

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
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            viewSettings {
                duration = 3000L
            }
        }.showTopToast()
    }

    /**
     * show TOP toast with auto remove, default layout
     * and custom text and colors
     */
    private fun showToastWithAutoRemoveCustomColorsAndText() {
        topToast {
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            viewSettings {
                duration = 3000L
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
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            toastGravity = TopToast.TOASTGRAVITY.BOTTOM
            viewSettings {
                duration = 3000L
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
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
            viewSettings {
                duration = 3000L
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
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                duration = 3000L
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
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                type = TOAST_TYPE.CUSTOM_VIEW
                customView = custom
            }
        }
        custom.findViewById<Button>(R.id.btn).setOnClickListener {
            toast.removeTopToast()
        }
        toast.showTopToast()
    }

    /**
     * show TOP toast with auto remove, swipe to remove and custom layoutId
     * use @layoutId if you do not need to setup you toast before show
     */
    private fun showToastWithCustomLayoutId() {
        val toast = topToast {
            context = this@MainActivity
            showAboveStatusBar = true
            viewSettings {
                type = TOAST_TYPE.CUSTOM_VIEW_ID
                layoutId = R.layout.custom_toast2
            }
        }
        toast.showTopToast()
    }

}

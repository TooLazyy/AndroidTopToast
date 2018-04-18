package ru.galt.toptoast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import ru.galt.toastlibrary.TopToast
import ru.galt.toastlibrary.builders.topToast
class MainActivity : AppCompatActivity() {

    private lateinit var toast: TopToast;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val custom = layoutInflater.inflate(R.layout.custom_toast, null)
        val btn = custom.findViewById<Button>(R.id.btn)
        toast = topToast {
            duration = 3000L
            removeOnSwipe = TopToast.TOASTSWIPE.TOP
            context = this@MainActivity
            showAboveStatusBar = false
        }
        btn.setOnClickListener {
            Handler(Looper.getMainLooper()).post({ toast.removeTopToast() })
        }

        findViewById<Button>(R.id.btn1).setOnClickListener {
            Handler(Looper.getMainLooper()).post({ toast.showTopToast() })
        }
    }
}

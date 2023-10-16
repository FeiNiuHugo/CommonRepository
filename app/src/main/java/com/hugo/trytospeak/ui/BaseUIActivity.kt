package com.hugo.trytospeak.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseUIActivity : AppCompatActivity() {

    companion object {
        const val TAG = "BaseUIActivity"
        const val DEBUG = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(android.R.color.transparent)
            navigationBarColor(android.R.color.transparent)
        }
    }
}
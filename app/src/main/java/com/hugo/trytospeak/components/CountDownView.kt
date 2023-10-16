package com.hugo.trytospeak.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hugo.trytospeak.R
import java.util.concurrent.TimeUnit

class CountDownView : AppCompatTextView, Runnable {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var countDown = 0
    private var start = false

    private var block: (() -> Unit)? = null

    fun countDown(countDown: Int, block: ()->Unit) {
        if (!start) {
            this.block = block
            removeCallbacks(this)
            postDelayed(this, TimeUnit.SECONDS.toMillis(1L))
            this.countDown = countDown
            refreshContent()
            start = true
        }
    }

    fun reset() {
        removeCallbacks(this)
        start = false
    }

    override fun run() {
        if (start) {
            if (--countDown > 0) {
                refreshContent()
                postDelayed(this, TimeUnit.SECONDS.toMillis(1L))
            } else {
                reset()
                block?.let { it() }
            }
        }
    }

    private fun refreshContent() {
        text = resources.getString(R.string.splash_count_down_template, countDown)
    }
}
package com.hugo.trytospeak.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ferfalk.simplesearchview.utils.DimensUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GameView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG and Paint.FILTER_BITMAP_FLAG)

    private var circleRadius = 0F
    private var distance = 0F

    private val curColors = mutableListOf<Int>()
    private var drawEvent: Flow<List<Int>>

    init {
        circleRadius = DimensUtils.convertDpToPx(20F, context)
        distance = DimensUtils.convertDpToPx(150F, context)
        drawEvent = flow {
            while (true) {
                do {
                    val colorTag = mutableListOf<Int>()
                    var complete = true
                    for (i in colorTable.indices) {
                        val color = colorTable[i].first
                        val times = timesTable[color]
                        colorTag.add(times?.let {
                            if (it > 0) {
                                timesTable[color] = it - 1
                                complete = false
                                1
                            } else 0
                        } ?: 0)
                    }
                    var colors = colorTag.mapIndexed { index, i ->
                        if (0 == i) colorTable[index].second
                        else {
                            colorTag[index] = 0
                            Color.BLACK
                        }
                    }
                    emit(colors)
                    delay(1000)
                    colors = colorTag.mapIndexed { index, i ->
                        if (0 == i) colorTable[index].second
                        else {
                            colorTag[index] = 0
                            Color.BLACK
                        }
                    }
                    emit(colors)
                    delay(1000)
                } while (!complete)
                delay(1000)
            }
        }
    }

    private val colors = setOf(RED, GREEN, BLUE, MAGENTA, YELLOW)
    private val times = setOf(ONE, TWO, THREE)

    private val timesTable = mutableMapOf<String, Int>()

    private val colorTable = listOf(
        YELLOW to Color.YELLOW,
        MAGENTA to Color.MAGENTA,
        BLUE to Color.BLUE,
        GREEN to Color.GREEN,
        RED to Color.RED)

    fun handle(cmd: String) {
        try {
            val color = cmd.substring(0, 2)
            val time = cmd.substring(2)
            if (color in colors && time in times) {
                val iTime = when (time) {
                    ONE -> 1
                    TWO -> 2
                    else -> 3
                }
                if (color in timesTable) {
                    timesTable[color] = timesTable[color]!! + iTime
                } else {
                    timesTable[color] = iTime
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val lifecycleOwner = findViewTreeLifecycleOwner()
        lifecycleOwner?.lifecycle?.apply {
            drawEvent.flowWithLifecycle(this)
        }
        lifecycleOwner?.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    drawEvent.collect {
                        curColors.clear()
                        curColors.addAll(it)
                        postInvalidate()
                    }
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBalls(canvas, curColors)
    }

    private fun drawBalls(canvas: Canvas, colors: List<Int>) {
        val unitAngle = 360F / colors.size
        canvas.save()
        colors.indices.forEach {
            val (centX, centY) = arrayOf(width / 2F, height / 2F)
            canvas.translate(centX, centY)
            canvas.rotate(it * unitAngle)
            canvas.translate(distance, 0F)
            paint.color = colors[it]
            canvas.drawCircle(0F, 0F, circleRadius, paint)
            canvas.translate(-distance, 0F)
            canvas.rotate(-it * unitAngle)
            canvas.translate(-centX, -centY)
        }
        canvas.restore()
    }

    companion object {
        const val RED = "红色"
        const val GREEN = "绿色"
        const val BLUE = "蓝色"
        const val MAGENTA = "品红"
        const val YELLOW = "黄色"

        const val ONE = "闪一下"
        const val TWO = "闪两下"
        const val THREE = "闪三下"
    }
}
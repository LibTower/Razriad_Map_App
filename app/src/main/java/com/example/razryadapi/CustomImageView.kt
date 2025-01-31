package com.example.razryadapi

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private val scaleGestureDetector: ScaleGestureDetector
    private val gestureDetector: GestureDetector
    private var scaleFactor = 1.0f
    private val matrix = Matrix()
    private var lastTouchPosition = PointF()
    private var isDragging = false
    private val matrixValues = FloatArray(9)

    init {
        scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f))
                updateMatrix()
                return true
            }
        })

        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                resetTransformations()
                return true
            }
        })
    }

    private fun updateMatrix() {
        matrix.reset()

        // Устанавливаем масштабирование
        val pivotX = width / 2f
        val pivotY = height / 2f

        matrix.postScale(scaleFactor, scaleFactor, pivotX, pivotY)

        // Перемещение изображения с учетом текущих координат
        if (drawable != null) {
            // Настройте translation так, чтобы изображение всегда было на экране
            adjustTranslation()
        }

        invalidate()
    }

    private fun adjustTranslation() {
        val drawableWidth = drawable.intrinsicWidth * scaleFactor
        val drawableHeight = drawable.intrinsicHeight * scaleFactor

        // Вычисляйте границы изображения, чтобы не выйти за пределы видимости
        val transX = when {
            drawableWidth < width -> (width - drawableWidth) / 2f // Выравнивание по центру
            else -> 0f // Можно перемещать в пределах видимого
        }

        val transY = when {
            drawableHeight < height -> (height - drawableHeight) / 2f // Выравнивание по центру
            else -> 0f // Можно перемещать в пределах видимого
        }

        matrix.postTranslate(transX, transY)

        // Применение трансляции к матрице
        matrix.preTranslate(-transX, -transY) // Обратите внимание на порядок
    }

    private fun resetTransformations() {
        scaleFactor = 1.0f
        updateMatrix()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        if (drawable != null) {
            canvas.concat(matrix)
            super.onDraw(canvas)
        }
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        handleTouchDrag(event)
        return true
    }

    private fun handleTouchDrag(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchPosition.set(event.x, event.y)
                isDragging = true
            }
            MotionEvent.ACTION_MOVE -> {
                if (isDragging) {
                    val dx = event.x - lastTouchPosition.x
                    val dy = event.y - lastTouchPosition.y
                    matrix.postTranslate(dx, dy)
                    lastTouchPosition.set(event.x, event.y)

                    // Проверка границ
                    adjustTranslation()
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragging = false
            }
        }
    }
}
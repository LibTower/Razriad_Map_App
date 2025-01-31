package com.example.razryadapi.Adapter_and_stuff

import android.view.View
import android.view.animation.AlphaAnimation

fun performClickAnimation(view: View, onComplete: () -> Unit) {
    // Создаем анимацию
    val animation = AlphaAnimation(1f, 0.6f) // Начальная и конечная прозрачность
    animation.duration = 100 // Длительность анимации
    animation.fillAfter = true // Сохраняем состояние после анимации

    // Устанавливаем слушатель на завершение анимации
    animation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
        override fun onAnimationStart(animation: android.view.animation.Animation) {}

        override fun onAnimationEnd(animation: android.view.animation.Animation) {
            // Восстанавливаем кнопку
            val fadeBack = AlphaAnimation(0.6f, 1f)
            fadeBack.duration = 100
            fadeBack.fillAfter = true
            view.startAnimation(fadeBack)

            fadeBack.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation) {
                    // Вызываем функцию завершения
                    onComplete()
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation) {}
            })
            view.startAnimation(fadeBack)
        }

        override fun onAnimationRepeat(animation: android.view.animation.Animation) {}
    })

    view.startAnimation(animation)
}

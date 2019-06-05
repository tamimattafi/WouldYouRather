package com.tamimattafi.wouldyourather.ui.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils


object CircularAnimation {

    var DEFAULT_DURATION = 300

    interface AnimationFinishedListener {
        fun onAnimationFinished()
    }

    fun createRevealSettingsFromView(rootView : View, startView : View): AnimationSettings {
        return AnimationSettings.createFromDimensions(
            (startView.x + startView.width / 2).toInt(),
            (startView.y + startView.height / 2).toInt(),
            rootView.width,
            rootView.height)
    }

    fun createRevealSettingsFromPoint(rootView: View?, x: Int, y: Int): AnimationSettings {
        return AnimationSettings.createFromDimensions(
            x,
            y,
            rootView?.width ?: 0,
            rootView?.height ?: 0)
    }

    fun getMediumDuration(context: Context): Int {
        return context.resources?.getInteger(android.R.integer.config_mediumAnimTime) ?: DEFAULT_DURATION
    }

    private fun registerCircularRevealAnimation(
        context: Context,
        view: View,
        revealSettings: AnimationSettings,
        listener: AnimationFinishedListener?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onLayoutChange(
                    v: View,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    v.removeOnLayoutChangeListener(this)
                    with(revealSettings) {
                        val finalRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()
                        ViewAnimationUtils.createCircularReveal(v, centerX, centerY, 0f, finalRadius).apply {
                            duration = getMediumDuration(context).toLong()
                            interpolator = FastOutSlowInInterpolator()
                            addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    listener?.onAnimationFinished()
                                }
                            })
                            start()
                        }
                    }
                }
            })
        } else {
            listener?.onAnimationFinished()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startCircularRevealExitAnimation(
        context: Context,
        view: View,
        revealSettings: AnimationSettings,
        listener: AnimationFinishedListener?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           with(revealSettings) {
               val initRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()
               ViewAnimationUtils.createCircularReveal(view, centerX, centerY, initRadius, 0f).apply {
                   duration = getMediumDuration(context).toLong()
                   interpolator = FastOutSlowInInterpolator()
                   addListener(object : AnimatorListenerAdapter() {
                       override fun onAnimationEnd(animation: Animator) {
                           view.visibility = View.GONE
                           listener?.onAnimationFinished()
                       }
                   })
                   start()
               }
           }
        } else {
            listener?.onAnimationFinished()
        }
    }

    fun registerRevealAnimation(
        context: Context,
        view: View,
        revealSettings: AnimationSettings,
        listener: AnimationFinishedListener? = null
    ) {
        registerCircularRevealAnimation(
            context,
            view,
            revealSettings,
            listener
        )
    }

    fun startExitAnimation(
        context: Context,
        view: View,
        revealSettings: AnimationSettings,
        listener: AnimationFinishedListener? = null
    ) {
        startCircularRevealExitAnimation(
            context,
            view,
            revealSettings,
            listener
        )
    }
}



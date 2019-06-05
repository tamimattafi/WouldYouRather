package com.tamimattafi.wouldyourather.ui.fragments.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamimattafi.wouldyourather.ui.animation.AnimationSettings
import com.tamimattafi.wouldyourather.ui.animation.CircularAnimation

abstract class BaseCircularFragment : Fragment() ,
    ControllableFragment {

    abstract val layoutId : Int
    var layout : View? = null
    abstract val settings : AnimationSettings?


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutId, container, false)?.apply {
            layout = this
            registerOpenAnimation()
            onViewCreated(this)
        }

    abstract fun onViewCreated(view: View)

    private fun registerOpenAnimation(listener: CircularAnimation.AnimationFinishedListener? = null) {
        CircularAnimation.registerRevealAnimation(
            context ?: return,
            layout ?: return,
            settings ?: return,
            listener
        )
    }

    private fun startCloseAnimation(listener: CircularAnimation.AnimationFinishedListener? = null) {
        CircularAnimation.startExitAnimation(
            context ?: return,
            layout ?: return,
            settings ?: return,
            listener
        )
    }

    override fun dismiss(listener: DismissListener?) {
        listener?.onFragmentPreDismiss()
        startCloseAnimation(object : CircularAnimation.AnimationFinishedListener {
            override fun onAnimationFinished() {
                listener?.onFragmentPostDismiss()
            }
        })
    }

}
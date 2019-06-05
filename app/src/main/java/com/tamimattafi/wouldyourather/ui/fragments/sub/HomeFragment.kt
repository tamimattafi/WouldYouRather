package com.tamimattafi.wouldyourather.ui.fragments.sub
import android.view.View
import com.tamimattafi.wouldyourather.R
import com.tamimattafi.wouldyourather.ui.animation.AnimationSettings
import com.tamimattafi.wouldyourather.ui.animation.CircularAnimation
import com.tamimattafi.wouldyourather.ui.fragments.base.BaseCircularFragment
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseCircularFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override val settings: AnimationSettings
        get() = CircularAnimation.createRevealSettingsFromPoint(
            layout,
            0,
            0
        )

    override fun onViewCreated(view: View) {
        view.apply {
            home_fragment_text.text = "View Created!"
        }
    }
}
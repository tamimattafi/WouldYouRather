package com.tamimattafi.wouldyourather.ui.fragments.managers

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.tamimattafi.wouldyourather.ui.fragments.base.ControllableFragment
import com.tamimattafi.wouldyourather.ui.fragments.base.DismissListener

class FragmentAttachementManager(
    val activity: AppCompatActivity,
    val fragmentsRoot : ViewGroup,
    val maxFragmentCount : Int) : DismissListener {

    private val layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT)

    override fun onFragmentPreDismiss(fragment: Fragment) {

    }

    override fun onFragmentPostDismiss(fragment: Fragment) {

    }

    private var currentFragment : Fragment? = null
    private val fragments = ArrayList<Fragment?>(maxFragmentCount)
    private val layoutsId = ArrayList<Int?>(maxFragmentCount)

    fun attachFragment(fragment: Fragment) {
        for (position in 0 until maxFragmentCount) {
            if (fragments[position] == null) {
                fragments[position] = fragment
                (fragment as ControllableFragment).attachDismissListener(this)
                showFragment(position)
                break
            }
        }
    }

    private fun showFragment(position : Int) {
        activity.supportFragmentManager.beginTransaction().apply {
            currentFragment = fragments[position]
            if (layoutsId[position] == null) {
                val newFrame = FrameLayout(activity).apply {
                    id = View.generateViewId()
                    layoutParams = this@FragmentAttachementManager.layoutParams
                }
                fragmentsRoot.addView(newFrame)
                layoutsId[position] = newFrame.id
            }
            add(layoutsId[position]!! , currentFragment!!)
            commitAllowingStateLoss()
            commit()
        }
    }
}
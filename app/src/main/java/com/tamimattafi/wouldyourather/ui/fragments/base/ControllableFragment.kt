package com.tamimattafi.wouldyourather.ui.fragments.base

import android.support.v4.app.Fragment

interface ControllableFragment {
    fun attachDismissListener(listener: DismissListener)
    fun dismiss(listener: DismissListener? = null)
}

interface DismissListener {
    fun onFragmentPreDismiss(fragment: Fragment)
    fun onFragmentPostDismiss(fragment: Fragment)
}
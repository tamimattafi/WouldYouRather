package com.tamimattafi.wouldyourather.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tamimattafi.wouldyourather.R
import com.tamimattafi.wouldyourather.ui.fragments.base.BaseCircularFragment
import com.tamimattafi.wouldyourather.ui.fragments.base.DismissListener
import com.tamimattafi.wouldyourather.ui.fragments.sub.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

package com.example.com.mptvshow.feature.main

import android.support.v4.app.Fragment
import android.widget.ImageView

interface MainListener {


    fun openFragment(fragment: Fragment, tag: String)
    fun openFragmentTransaction(fragment: Fragment, tag: String, imageView: ImageView)

}
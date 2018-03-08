package com.example.com.mptvshow.feature.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.addFragment
import com.example.com.mptvshow.extensions.replaceFragment
import com.example.com.mptvshow.extensions.replaceFragmentTransition
import com.example.com.mptvshow.feature.home.ui.ListTvShowsFragment

class MainActivity : AppCompatActivity(), MainListener {

    companion object {

        fun launchIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            addFragment(ListTvShowsFragment.newInstance())
    }


    override fun openFragment(fragment: Fragment, tag: String) {
        replaceFragment(fragment, tag)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun openFragmentTransaction(fragment: Fragment, tag: String, imageView: ImageView) {
        replaceFragmentTransition(fragment, imageView, tag)
    }


}

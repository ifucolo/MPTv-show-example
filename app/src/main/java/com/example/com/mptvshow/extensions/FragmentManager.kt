package com.example.com.mptvshow.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.com.mptvshow.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

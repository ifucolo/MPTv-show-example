package com.example.com.mptvshow.extensions

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewCompat
import android.widget.ImageView
import com.example.com.mptvshow.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

inline fun FragmentManager.inTransactionImage(func: FragmentTransaction.() -> Unit, imageView: ImageView) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.addSharedElement(imageView, ViewCompat.getTransitionName(imageView))
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


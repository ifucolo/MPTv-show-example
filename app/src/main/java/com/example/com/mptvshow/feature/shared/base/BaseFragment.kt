package com.example.com.mptvshow.feature.shared.base

import android.content.Context
import android.support.v4.app.Fragment
import com.example.com.mptvshow.feature.main.MainListener
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import io.reactivex.disposables.Disposable

import java.util.ArrayList

open class BaseFragment: Fragment() {

    protected var mainListener: MainListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mainListener = context as MainListener
    }

    override fun onDetach() {
        mainListener = null

        super.onDetach()
    }
}
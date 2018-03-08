package com.example.com.mptvshow.feature.shared.base

import android.content.Context
import android.support.v4.app.Fragment
import com.example.com.mptvshow.feature.main.MainListener
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import io.reactivex.disposables.Disposable

import java.util.ArrayList

open class BaseFragment: Fragment() {

    private var mCallList: ArrayList<Call<*>>? = null
    private var disposables: CompositeDisposable? = null
    protected var mainListener: MainListener? = null

    protected fun addCallRequest(call: Call<*>) {
        if (mCallList == null)
            mCallList = ArrayList()

        mCallList?.add(call)
    }

    protected fun addDisposable(disposable: Disposable?) {
        if (disposables == null)
            disposables = CompositeDisposable()

        disposable?.let { disposables?.add(it) }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mainListener = context as MainListener
    }

    override fun onDetach() {
        mainListener = null

        super.onDetach()
    }

    protected fun cancelAllRequests() {
        if (mCallList != null) {
            mCallList.let {
                for (call in mCallList as ArrayList) {
                    if (!call.isCanceled) {
                        call.cancel()
                    }
                }

                mCallList?.clear()
            }
        }

        if (disposables != null) {
            disposables?.clear()
        }
    }

    override fun onDestroyView() {
        cancelAllRequests()
        mCallList = null
        disposables = null

        super.onDestroyView()
    }
}
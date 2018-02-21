package com.example.com.mptvshow.feature

import android.content.Context
import android.os.Build
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.example.com.mptvshow.feature.detail.ui.TvShowDetailFragment
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.list.ui.ListTvShowAdapter
import com.example.com.mptvshow.feature.main.MainListener
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import io.reactivex.disposables.Disposable

import java.util.ArrayList

open class BaseFragment: Fragment(), ListTvShowAdapter.ListGenericListener {

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

    override fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mainListener?.openFragmentTransaction(TvShowDetailFragment.newInstance(tvShowItem, imgCover.transitionName), "", imgCover)
        else
            mainListener?.openFragment(TvShowDetailFragment.newInstance(tvShowItem), "")

    }
}
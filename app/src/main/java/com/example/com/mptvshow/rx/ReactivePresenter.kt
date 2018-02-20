package com.example.com.mptvshow.rx

import io.reactivex.disposables.CompositeDisposable

open class ReactivePresenter<V> {
    val disposables = CompositeDisposable()
    var view: V? = null
        private set

    fun disposables() = disposables
    fun view() = view

    open fun bind(view: V) {
        this.view = view
    }

    fun unbind() {
        this.disposables.clear()
        this.view = null
    }

    protected val isBinded: Boolean
        get() = this.view != null
}
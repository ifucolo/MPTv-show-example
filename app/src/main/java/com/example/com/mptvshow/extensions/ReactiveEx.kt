package com.example.com.mptvshow.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

 operator fun CompositeDisposable.plusAssign(subscribe: Disposable?) {}

package com.example.com.mptvshow.extensions

import io.reactivex.Completable
import io.reactivex.Observable

inline fun <T : Any> Observable<T>.toCompletable(): Completable = Completable.fromObservable(this)
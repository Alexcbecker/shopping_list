package com.domain.usecase

import com.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread
) {
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    open fun execute(
            params: Params,
            onNext: (T) -> Unit,
            onError: (e: Throwable) -> Unit,
            onComplete: (() -> Unit)? = null) {
        try {
            val observable = this.buildUseCaseObservable(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(postExecutionThread.scheduler)
            addDisposable(observable.subscribeWith(object : DisposableObserver<T>() {
                override fun onComplete() {
                    onComplete?.invoke()
                }

                override fun onNext(t: T) {
                    onNext.invoke(t)
                }

                override fun onError(e: Throwable) {
                    onError.invoke(e)
                }
            }))
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    fun dispose() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
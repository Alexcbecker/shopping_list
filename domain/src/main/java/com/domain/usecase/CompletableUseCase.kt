package com.domain.usecase

import com.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread
) {
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params): Completable

    open fun execute(
            params: Params,
            complete: () -> Unit,
            error: (e: Throwable) -> Unit) {
        try {
            val completable = this.buildUseCaseCompletable(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(postExecutionThread.scheduler)

            addDisposable(completable.subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    complete()
                }

                override fun onError(e: Throwable) {
                    error(e)
                }
            }))

        } catch (e: Throwable) {
            error(e)
        }
    }

    fun dispose() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
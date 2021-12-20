package com.example.gbpopularlibs.mvp


import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CalculatePresenter: CalculateContract.Presenter() {
    var subject = BehaviorSubject.createDefault(0L)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onSubscribe()
    }
    override fun squareNumber(number: Long){
        subject.onNext(number)
    }

    private fun onSubscribe() {
        subject
            .map { it * it }
            .subscribeOn(Schedulers.computation())
            .subscribe ({viewState.showCalculateResult(it.toString())}, {})
    }

}
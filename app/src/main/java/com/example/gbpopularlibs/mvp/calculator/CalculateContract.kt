package com.example.gbpopularlibs.mvp.calculator

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class CalculateContract {
    @AddToEndSingle
    interface View : MvpView {

        fun onButtonCalculateClick()

        fun showCalculateResult(text : String)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun squareNumber(number : Long)
    }
}
package com.example.gbpopularlibs.mvp

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class UsersContract {
    enum class State {
        LOADING, IDLE
    }
    @AddToEndSingle
    interface View : MvpView {
        fun onItemClick(pos : Int)
        fun setState(state : State)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun showUser(login : String)
    }
}
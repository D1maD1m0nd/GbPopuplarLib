package com.example.gbpopularlibs.mvp

import com.example.gbpopularlibs.data.model.GitHubUser
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
        fun showUsersList(users : List<GitHubUser>)
        fun setState(state : State)

    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun showUser(login : String)
        abstract fun getUsers()
    }
}
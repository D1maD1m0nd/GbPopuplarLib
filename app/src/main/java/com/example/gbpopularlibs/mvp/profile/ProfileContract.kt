package com.example.gbpopularlibs.mvp.profile

import com.example.gbpopularlibs.data.model.GitHubRepo
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

class ProfileContract {
    enum class AuthState {
        ERROR, SUCCESS, LOADING
    }

    interface View : MvpView {
        @Skip
        fun setState(state: AuthState)
        @AddToEndSingle
        fun setRepoList(list : List<GitHubRepo>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun initReposList(login: String)
    }
}
package com.example.gbpopularlibs.mvp.auth

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

class AuthContract {
    enum class AuthState {
        ERROR, SUCCESS
    }

    enum class PasswordState {
        SUCCESS, INCORRECT_PASSWORD
    }

    enum class LoginState {
        SUCCESS, INCORRECT_LOGIN
    }

    interface View : MvpView {

        @Skip
        fun setAuthState(state: AuthState)

        @AddToEndSingle
        fun setPasswordState(state: PasswordState)

        @AddToEndSingle
        fun setLoginState(state: LoginState)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onChangeLogin(login: String)
        abstract fun onChangePassword(password: String)
        abstract fun onAuthButtonClick(login: String, password: String)
        abstract fun showUserScreen()
    }
}
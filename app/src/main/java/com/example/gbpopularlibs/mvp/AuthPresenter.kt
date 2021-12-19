package com.example.gbpopularlibs.mvp

import com.example.gbpopularlibs.data.model.repository.Repository
import com.example.gbpopularlibs.data.model.repository.RepositoryImpl
import com.example.gbpopularlibs.utils.Screens
import com.example.gbpopularlibs.utils.ValidateAuth
import com.github.terrakok.cicerone.Router


class AuthPresenter(
    private val router: Router,
    private val validateAuth: ValidateAuth = ValidateAuth(),
    private val repo: Repository = RepositoryImpl()
) : AuthContract.Presenter() {
    override fun onChangeLogin(login: String) {
        val isValid = validateAuth.checkLogin(login)
        val state = if (isValid) {
            AuthContract.LoginState.SUCCESS
        } else {
            AuthContract.LoginState.INCORRECT_LOGIN
        }
        viewState.setLoginState(state)
    }

    override fun onChangePassword(password: String) {
        val isValid = validateAuth.checkPassword(password)
        val state = if (isValid) {
            AuthContract.PasswordState.SUCCESS
        } else {
            AuthContract.PasswordState.INCORRECT_PASSWORD
        }
        viewState.setPasswordState(state)
    }

    override fun onAuthButtonClick(login: String, password: String) {
        val isValidLogin = validateAuth.checkLogin(login)
        if (isValidLogin.not()) {
            viewState.setLoginState(AuthContract.LoginState.INCORRECT_LOGIN)
        }

        val isValidPassword = validateAuth.checkPassword(password)
        if (isValidPassword.not()) {
            viewState.setPasswordState(AuthContract.PasswordState.INCORRECT_PASSWORD)
        }

        repo.getUser(login, password).subscribe( {user ->
                if (user != null) {
                    viewState.setAuthState(AuthContract.AuthState.SUCCESS)
                    router.navigateTo(Screens.Profile(user))
                } else {
                    viewState.setAuthState(AuthContract.AuthState.ERROR)
                }
            },
            {}
        )
    }

    override fun showUserScreen() {
        TODO("Not yet implemented")
    }
}
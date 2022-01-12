package com.example.gbpopularlibs.mvp.user

import com.example.gbpopularlibs.data.model.repository.GitHubUserRepository
import com.example.gbpopularlibs.utils.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UsersPresenter(
): UsersContract.Presenter() {

    @Inject lateinit var repo: GitHubUserRepository
    @Inject lateinit var router: Router
    @Inject lateinit var screens: Screens

    override fun showUser(login: String) {
        repo.getGitHubUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.setState(UsersContract.State.LOADING)
            }
            .subscribe({
                viewState.setState(UsersContract.State.IDLE)
                router.navigateTo(screens.Profile(it))
            },{
                val errorMessage = it.message
                viewState.setState(UsersContract.State.IDLE)
            })
    }

    override fun getUsers() {
        repo.getGitHubUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.setState(UsersContract.State.LOADING)
            }
            .subscribe({
                viewState.setState(UsersContract.State.IDLE)
                viewState.showUsersList(it)
            },{})
    }

}
package com.example.gbpopularlibs.mvp

import com.example.gbpopularlibs.data.model.repository.GitHubUserRepository
import com.example.gbpopularlibs.data.model.repository.GitHubUserRepositoryImpl
import com.example.gbpopularlibs.data.model.repository.Repository
import com.example.gbpopularlibs.data.model.repository.RepositoryImpl
import com.example.gbpopularlibs.utils.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersPresenter(
    private val router : Router,
    private val repo: GitHubUserRepository= GitHubUserRepositoryImpl()
): UsersContract.Presenter() {
    override fun showUser(login: String) {
        repo.getGitHubUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.setState(UsersContract.State.LOADING)
            }
            .subscribe({
                viewState.setState(UsersContract.State.IDLE)
                router.navigateTo(Screens.Profile(it))
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
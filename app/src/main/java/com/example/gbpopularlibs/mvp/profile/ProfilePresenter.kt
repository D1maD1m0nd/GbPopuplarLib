package com.example.gbpopularlibs.mvp.profile

import com.example.gbpopularlibs.data.model.repository.GitHubRepoRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter : ProfileContract.Presenter() {
    @Inject
    lateinit var repo: GitHubRepoRepository

    override fun initReposList(login: String) {
        repo.getUserRepo(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{viewState.setState(ProfileContract.AuthState.LOADING)}
            .subscribe({
                     viewState.setState(ProfileContract.AuthState.SUCCESS)
                     viewState.setRepoList(it)
            },{
                viewState.setState(ProfileContract.AuthState.ERROR)
            })
    }
}
package com.example.gbpopularlibs.di

import com.example.gbpopularlibs.MainActivity
import com.example.gbpopularlibs.framework.AuthorizationFragment
import com.example.gbpopularlibs.framework.UsersFragment
import com.example.gbpopularlibs.mvp.AuthPresenter
import com.example.gbpopularlibs.mvp.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        CiceroneModule::class,
        RepoModule::class,
        RoomModule::class
    ])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: AuthorizationFragment)
    fun inject(fragment: UsersFragment)
    fun inject(presenter: UsersPresenter)
    fun inject(presenter: AuthPresenter)

}
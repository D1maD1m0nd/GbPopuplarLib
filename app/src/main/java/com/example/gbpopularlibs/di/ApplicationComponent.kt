package com.example.gbpopularlibs.di

import android.content.Context
import com.example.gbpopularlibs.MainActivity
import com.example.gbpopularlibs.di.components.ProfileComponent
import com.example.gbpopularlibs.framework.AuthorizationFragment
import com.example.gbpopularlibs.framework.ProfileFragment
import com.example.gbpopularlibs.framework.UsersFragment
import com.example.gbpopularlibs.mvp.auth.AuthPresenter
import com.example.gbpopularlibs.mvp.profile.ProfilePresenter
import com.example.gbpopularlibs.mvp.user.UsersPresenter
import dagger.BindsInstance
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
    fun provideProfileComponent(): ProfileComponent.Builder
    fun inject(activity: MainActivity)
    fun inject(fragment: AuthorizationFragment)
    fun inject(fragment: UsersFragment)
    fun inject(presenter: UsersPresenter)
    fun inject(presenter: AuthPresenter)
    fun inject(presenter: ProfilePresenter)
    fun inject(fragment: ProfileFragment)

}
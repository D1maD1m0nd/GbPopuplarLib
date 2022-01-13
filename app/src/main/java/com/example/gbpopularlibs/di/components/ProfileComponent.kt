package com.example.gbpopularlibs.di.components

import com.example.gbpopularlibs.mvp.profile.ProfilePresenter
import dagger.Subcomponent
import javax.inject.Scope


@FragmentScope
@Subcomponent(
    modules = [ProfileRepoModule::class]
)
interface ProfileComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProfileComponent
    }

    fun inject(presenter: ProfilePresenter)
}

@Scope
annotation class FragmentScope

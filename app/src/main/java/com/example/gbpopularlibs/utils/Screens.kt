package com.example.gbpopularlibs.utils

import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.model.User
import com.example.gbpopularlibs.framework.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


class Screens {
    fun Profile(user: User) = FragmentScreen { ProfileFragment.newInstance(user) }
    fun Profile(user: GitHubUser) = FragmentScreen { ProfileFragment.newInstance(user) }
}

package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class User(val login: String,
                val password: String,
                val isActive : Boolean = Random.nextBoolean(),
                var repo : UserRepository? = null) : Parcelable

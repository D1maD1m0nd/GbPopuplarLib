package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val login: String, val password: String) : Parcelable

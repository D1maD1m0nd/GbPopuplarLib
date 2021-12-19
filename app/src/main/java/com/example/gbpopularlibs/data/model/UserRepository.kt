package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserRepository(var userLogin : String) : Parcelable
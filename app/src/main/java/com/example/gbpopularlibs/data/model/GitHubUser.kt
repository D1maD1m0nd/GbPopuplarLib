package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(

	@field:Json(name="bio")
	val bio: String? = null,

	@field:Json(name="created_at")
	val createdAt: String? = null,

	@field:Json(name="login")
	val login: String? = null,

	@field:Json(name="type")
	val type: String? = null,

	@field:Json(name="avatar_url")
	val avatarUrl: String? = null,
) : Parcelable

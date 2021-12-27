package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "GitHubUserTable")
@Parcelize
data class GitHubUser(

	@PrimaryKey
	@field:Json(name="id")
	var id : Int? = null,

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

	@field:Json(name="url")
	val url : String? = null
) : Parcelable

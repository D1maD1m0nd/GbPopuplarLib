package com.example.gbpopularlibs.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "GitHubRepoTable")
@Parcelize
data class GitHubRepo(
    @PrimaryKey
    @field:Json(name="id")
    val id : String,
    @field:Json(name="name")
    val name : String,
    var ownerLogin : String? = null
) : Parcelable

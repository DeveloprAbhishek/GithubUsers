package com.abhishek.githubusers.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserRepositoryDto(
    val description: String?,
    val fork: Boolean?,
    @Json(name = "forks_count")
    val forksCount: Int?,
    @Json(name = "full_name")
    val fullName: String?,
    val id: Int?,
    val language: String?,
    val name: String?,
    val private: Boolean?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int?
) : Parcelable

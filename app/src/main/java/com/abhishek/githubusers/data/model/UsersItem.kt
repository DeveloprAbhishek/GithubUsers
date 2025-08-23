package com.abhishek.githubusers.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UsersItem(
    val id: Int,
    val login: String,
    @Json(name = "avatarUrl") val avatarUrl: String,
    @Json(name = "events_url") val eventsUrl: String,
    val gists_url: String,
    val gravatar_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val url: String,
    val html_url: String, // not required
    val site_admin: Boolean, // not required
    val type: String, // not required
    val user_view_type: String, // not required
    val followers_url: String, // not required
    val following_url: String, // not required
    val node_id: String, // not required
) : Parcelable

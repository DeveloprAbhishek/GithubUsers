package com.abhishek.githubusers.data.model

import android.os.Parcelable
import com.abhishek.githubusers.ui.model.UsersItemUi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UsersItemDto(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "events_url") val eventsUrl: String,
    @Json(name = "gists_url") val gistsUrl: String,
    @Json(name = "gravatar_id") val gravatarId: String,
    @Json(name = "organizations_url") val organizationsUrl: String,
    @Json(name = "received_events_url") val receivedEventsUrl: String,
    @Json(name = "repos_url") val reposUrl: String,
    @Json(name = "starred_url") val starredUrl: String,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String,
    val url: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "site_admin") val siteAdmin: Boolean,
    val type: String,
    @Json(name = "user_view_type") val userViewType: String,
    @Json(name = "followers_url") val followersUrl: String,
    @Json(name = "following_url") val followingUrl: String,
    @Json(name = "node_id") val nodeId: String,
) : Parcelable

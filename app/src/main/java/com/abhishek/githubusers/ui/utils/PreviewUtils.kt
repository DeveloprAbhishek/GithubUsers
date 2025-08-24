package com.abhishek.githubusers.ui.utils

import com.abhishek.githubusers.data.model.UsersItemDto
import com.abhishek.githubusers.ui.model.UsersItemUi

object PreviewUtils {
    fun getUserData(): UsersItemDto = UsersItemDto(
        id = 101,
        login = "dummy_user",
        avatarUrl = "https://example.com/avatar.png",
        eventsUrl = "https://api.github.com/users/dummy_user/events{/privacy}",
        gistsUrl = "https://api.github.com/users/dummy_user/gists{/gist_id}",
        gravatarId = "abc123xyz",
        organizationsUrl = "https://api.github.com/users/dummy_user/orgs",
        receivedEventsUrl = "https://api.github.com/users/dummy_user/received_events",
        reposUrl = "https://api.github.com/users/dummy_user/repos",
        starredUrl = "https://api.github.com/users/dummy_user/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/dummy_user/subscriptions",
        url = "https://api.github.com/users/dummy_user",
        htmlUrl = "https://github.com/dummy_user",
        siteAdmin = false,
        type = "User",
        userViewType = "public",
        followersUrl = "https://api.github.com/users/dummy_user/followers",
        followingUrl = "https://api.github.com/users/dummy_user/following{/other_user}",
        nodeId = "MDQ6VXNlcjEwMQ=="
    )

    fun getUserItemList(): List<UsersItemUi> = List(6) {
        UsersItemUi(
            id = it,
            login = "dummy_user_$it",
            avatarUrl = "https://example.com/avatar.png"
        )
    }
}

package com.abhishek.githubusers.util

import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.model.UserDetailsDto
import com.abhishek.githubusers.data.model.UserRepositoryDto
import com.abhishek.githubusers.data.model.UsersItemDto

object FakeUsersData {
    fun getFakeUserRepository() = UserRepositoryDto(
        description = "A sample Android project built with Jetpack Compose",
        fork = false,
        forksCount = 120,
        fullName = "dummyuser/compose-sample",
        id = 987654,
        language = "Kotlin",
        name = "compose-sample",
        private = false,
        stargazersCount = 540
    )

    fun getFakeUserRepositories(
        count: Int = 5,
        user: String = "dummyuser"
    ): List<UserRepositoryDto> =
        List(count) { index ->
            UserRepositoryDto(
                description = "Repository $index description for $user",
                fork = index % 2 == 0, // alternate forked / not forked
                forksCount = 10 * (index + 1),
                fullName = "$user/repo-$index",
                id = index + 1,
                language = listOf("Kotlin", "Python", "Go", "JavaScript", "Rust")[index % 5],
                name = "repo-$index",
                private = index % 3 == 0, // every 3rd one is private
                stargazersCount = 100 * (index + 1)
            )
        }

    fun getFakeUserDetails() = UserDetailsDto(
        avatarUrl = "https://avatars.githubusercontent.com/u/1234567?v=4",
        createdAt = "2018-05-20T14:25:43Z",
        publicRepos = 42,
        bio = "Android Developer • Open Source Enthusiast • Coffee Lover ☕",
        blog = "https://dummydev.blog",
        company = "OpenAI Inc.",
        email = "dummyuser@example.com",
        followers = 1200,
        following = 150,
        id = 1234567,
        location = "San Francisco, CA",
        login = "dummyuser",
        name = "Dummy User"
    )

    fun getFakeUserDetailsList(): List<UserDetailsDto> = List(5) { index ->
        UserDetailsDto(
            avatarUrl = "https://avatars.githubusercontent.com/u/${1000 + index}?v=4",
            createdAt = "2020-01-${10 + index}T12:00:00Z",
            publicRepos = 10 + index,
            bio = "Bio for dummy user $index",
            blog = "https://blog$index.example.com",
            company = "Company $index",
            email = "user$index@example.com",
            followers = 100 * (index + 1),
            following = 50 * (index + 1),
            id = 1000 + index,
            location = "City $index",
            login = "dummyuser$index",
            name = "Dummy User $index"
        )
    }

    fun getFakeUserItem() = UsersItemDto(
        id = 101,
        login = "dummyuser",
        avatarUrl = "https://avatars.githubusercontent.com/u/101?v=4",
        eventsUrl = "https://api.github.com/users/dummyuser/events{/privacy}",
        gistsUrl = "https://api.github.com/users/dummyuser/gists{/gist_id}",
        gravatarId = "",
        organizationsUrl = "https://api.github.com/users/dummyuser/orgs",
        receivedEventsUrl = "https://api.github.com/users/dummyuser/received_events",
        reposUrl = "https://api.github.com/users/dummyuser/repos",
        starredUrl = "https://api.github.com/users/dummyuser/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/dummyuser/subscriptions",
        url = "https://api.github.com/users/dummyuser",
        htmlUrl = "https://github.com/dummyuser",
        siteAdmin = false,
        type = "User",
        userViewType = "public",
        followersUrl = "https://api.github.com/users/dummyuser/followers",
        followingUrl = "https://api.github.com/users/dummyuser/following{/other_user}",
        nodeId = "MDQ6VXNlcjEwMQ=="
    )


    fun getFakeUserList(): List<UsersItemDto> = List(5) { index ->
        UsersItemDto(
            id = index + 1,
            login = "dummyuser$index",
            avatarUrl = "https://avatars.githubusercontent.com/u/${1000 + index}?v=4",
            eventsUrl = "https://api.github.com/users/dummyuser$index/events{/privacy}",
            gistsUrl = "https://api.github.com/users/dummyuser$index/gists{/gist_id}",
            gravatarId = "",
            organizationsUrl = "https://api.github.com/users/dummyuser$index/orgs",
            receivedEventsUrl = "https://api.github.com/users/dummyuser$index/received_events",
            reposUrl = "https://api.github.com/users/dummyuser$index/repos",
            starredUrl = "https://api.github.com/users/dummyuser$index/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/dummyuser$index/subscriptions",
            url = "https://api.github.com/users/dummyuser$index",
            htmlUrl = "https://github.com/dummyuser$index",
            siteAdmin = index % 2 == 0,
            type = "User",
            userViewType = "public",
            followersUrl = "https://api.github.com/users/dummyuser$index/followers",
            followingUrl = "https://api.github.com/users/dummyuser$index/following{/other_user}",
            nodeId = "MDQ6VXNlcjEw${index}=="
        )
    }

    fun getFakeUserEntities(count: Int = 10): List<UserEntity> =
        List(count) { index ->
            UserEntity(
                id = index + 1,
                login = "dummyuser${index + 1}",
                avatarUrl = "https://avatars.githubusercontent.com/u/${index + 1}?v=4"
            )
        }
}

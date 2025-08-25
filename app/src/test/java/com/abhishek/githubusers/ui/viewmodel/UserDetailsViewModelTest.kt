@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package com.abhishek.githubusers.ui.viewmodel

import app.cash.turbine.test
import com.abhishek.githubusers.UserDetailsActivity.Companion.USER_NAME
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.ui.model.UserDetailsUi
import com.abhishek.githubusers.ui.model.UserRepositoryUi
import com.abhishek.githubusers.util.MainDispatcherRule
import com.abhishek.githubusers.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: UsersRepository

    @Before
    fun setUp() {
        repository = mock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `emits Loading then Success when repository returns Success`() = runTest {
        val details = UserDetailsUi(
            avatarUrl = "https://x/1",
            publicRepos = 10,
            bio = "bio",
            blog = "blog",
            company = "company",
            email = "a@b.com",
            followers = 1,
            following = 2,
            location = "Earth",
            login = "octocat",
            name = "Octo Cat"
        )
        val repos = listOf(
            UserRepositoryUi(
                id = 1,
                description = "repo",
                fork = false,
                forksCount = 1,
                fullName = "octo/repo",
                language = "Kotlin",
                name = "repo",
                isPrivate = false,
                stargazersCount = 7
            )
        )
        whenever(repository.getUserDetailsWithRepos("octocat"))
            .thenReturn(flowOf(Result.Success(details to repos)))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "octocat"))
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            val success = awaitItem() as UserDetailsUiState.Success
            assertEquals(details, success.userDetails)
            assertEquals(repos, success.repositories)
        }
    }

    @Test
    fun `emits Loading then Error when repository returns Error`() = runTest {
        whenever(repository.getUserDetailsWithRepos("broken"))
            .thenReturn(flowOf(Result.Error("boom")))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "broken"))
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            val error = awaitItem() as UserDetailsUiState.Error
            assertEquals("boom", error.message)
        }
    }

    @Test
    fun `emits Loading when repository returns Loading`() = runTest {
        whenever(repository.getUserDetailsWithRepos("octo"))
            .thenReturn(flowOf(Result.Loading))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "octo"))
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emits Loading then Success when repository goes from Loading to Success`() = runTest {
        val details = UserDetailsUi(
            avatarUrl = "",
            publicRepos = 0,
            bio = null,
            blog = null,
            company = null,
            email = null,
            followers = 0,
            following = 0,
            location = null,
            login = "octo",
            name = "Octo"
        )
        val repos = emptyList<UserRepositoryUi>()

        whenever(repository.getUserDetailsWithRepos("octo"))
            .thenReturn(
                flowOf(Result.Loading, Result.Success(details to repos))
            )

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "octo"))
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            val success = awaitItem() as UserDetailsUiState.Success
            assertEquals("octo", success.userDetails.login)
        }
    }

    @Test
    fun `uses empty username when SavedStateHandle has no key`() = runTest {
        whenever(repository.getUserDetailsWithRepos(""))
            .thenReturn(flowOf(Result.Error("missing")))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle()
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            val error = awaitItem() as UserDetailsUiState.Error
            assertEquals("missing", error.message)
        }
        verify(repository).getUserDetailsWithRepos("")
    }

    @Test
    fun `forwards non-empty username from SavedStateHandle to repository`() = runTest {
        whenever(repository.getUserDetailsWithRepos("alice"))
            .thenReturn(flowOf(Result.Loading))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "alice"))
        )

        vm.uiState.test {
            awaitItem()
            cancelAndIgnoreRemainingEvents()
        }

        verify(repository).getUserDetailsWithRepos("alice")
    }

    @Test
    fun `emits Loading then Error when repository goes from Loading to Error`() = runTest {
        whenever(repository.getUserDetailsWithRepos("bob"))
            .thenReturn(flowOf(Result.Loading, Result.Error("network")))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(mapOf(USER_NAME to "bob"))
        )

        vm.uiState.test {
            assertTrue(awaitItem() is UserDetailsUiState.Loading)
            val err = awaitItem() as UserDetailsUiState.Error
            assertEquals("network", err.message)
        }
    }

    @Test
    fun `emits Success with empty repositories list`() = runTest {
        val detailsUi = UserDetailsUi(
            avatarUrl = "https://avatars.githubusercontent.com/u/42?v=4",
            publicRepos = 10,
            bio = "bio",
            blog = "https://blog.example",
            company = "Example Co.",
            email = "me@example.com",
            followers = 1,
            following = 2,
            location = "Earth",
            login = "zero",
            name = "Zero"
        )
        val reposUi = emptyList<UserRepositoryUi>()

        whenever(repository.getUserDetailsWithRepos("zero"))
            .thenReturn(flowOf(Result.Success(detailsUi to reposUi)))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(
                mapOf(USER_NAME to "zero")
            )
        )

        vm.uiState.test {
            assert(awaitItem() is UserDetailsUiState.Loading)
            val s = awaitItem() as UserDetailsUiState.Success
            assertEquals(detailsUi.login, s.userDetails.login)
            assertTrue(s.repositories.isEmpty())
        }
    }

    @Test
    fun `emits Success and preserves nullable fields`() = runTest {
        val detailsUi = UserDetailsUi(
            avatarUrl = "https://avatars.githubusercontent.com/u/42?v=4",
            publicRepos = 10,
            bio = "bio",
            blog = "https://blog.example",
            company = "Example Co.",
            email = "me@example.com",
            followers = 1,
            following = 2,
            location = "Earth",
            login = "zero",
            name = "Zero"
        )
        val reposUi = emptyList<UserRepositoryUi>()

        whenever(repository.getUserDetailsWithRepos("nullable"))
            .thenReturn(flowOf(Result.Success(detailsUi to reposUi)))

        val vm = UserDetailsViewModel(
            usersRepository = repository,
            savedStateHandle = androidx.lifecycle.SavedStateHandle(
                mapOf(USER_NAME to "nullable")
            )
        )

        vm.uiState.test {
            assert(awaitItem() is UserDetailsUiState.Loading)
            val s = awaitItem() as UserDetailsUiState.Success
            assertEquals(detailsUi.login, s.userDetails.login)
            assertTrue(s.repositories.isEmpty())
        }
    }

}

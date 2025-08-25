@file:OptIn(
    kotlinx.coroutines.ExperimentalCoroutinesApi::class,
    kotlinx.coroutines.FlowPreview::class
)

package com.abhishek.githubusers.ui.viewmodel

import app.cash.turbine.test
import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.ui.model.UsersItemUi
import com.abhishek.githubusers.util.FakeUsersData
import com.abhishek.githubusers.util.MainDispatcherRule
import com.abhishek.githubusers.utils.AppConstants.DEBOUNCE_TIMEOUT
import com.abhishek.githubusers.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UsersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: UsersRepository
    private lateinit var mapper: UserUiMapper
    private lateinit var viewModel: UsersViewModel

    @Before
    fun setup() {
        repository = mock()
        mapper = mock()

        whenever(mapper.entityToUi(any())).thenAnswer { inv ->
            val e = inv.arguments[0] as UserEntity
            UsersItemUi(id = e.id, login = e.login, avatarUrl = e.avatarUrl)
        }

        whenever(repository.refreshUsers()).thenReturn(flow { emit(Result.Loading) })
        whenever(repository.getAllUsers()).thenReturn(flowOf(FakeUsersData.getFakeUserEntities(3)))
        whenever(repository.searchUsers(any())).thenReturn(flowOf(emptyList()))

        viewModel = UsersViewModel(repository, mapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `search emits Success when repo returns entities`() = runTest {
        val entities = listOf(UserEntity(1, "octocat", "https://x/1"))
        whenever(repository.searchUsers("octo")).thenReturn(flowOf(entities))

        viewModel.usersUiState.test {
            val first = awaitItem()
            viewModel.onSearchQueryChanged("octo")
            advanceTimeBy(DEBOUNCE_TIMEOUT)
            advanceUntilIdle()
            val state = awaitItem()
            assert(state is UsersUiState.Success)
        }
    }

    @Test
    fun `initial state is Loading then emits Success after debounce from DB flow`() = runTest {
        whenever(repository.getAllUsers()).thenReturn(flowOf(FakeUsersData.getFakeUserEntities(3)))

        viewModel.usersUiState.test {
            val initial = awaitItem()
            runCurrent()
            advanceTimeBy(DEBOUNCE_TIMEOUT)
            advanceUntilIdle()
            val success = awaitItem() as UsersUiState.Success
            assertEquals(3, success.users.size)
        }
    }

    @Test
    fun `search query triggers searchUsers and emits Success when results map via mapper`() =
        runTest {
            val entities = listOf(
                UserEntity(1, "octocat", "https://x/1"),
                UserEntity(2, "hubber", "https://x/2")
            )
            whenever(repository.searchUsers("hub")).thenReturn(flowOf(entities))

            viewModel.usersUiState.test {
                val initial = awaitItem()
                viewModel.onSearchQueryChanged("hub")
                advanceTimeBy(DEBOUNCE_TIMEOUT)
                advanceUntilIdle()
                val state = awaitItem()
                val success = state as UsersUiState.Success
                assertEquals(2, success.users.size)
                assertEquals("octocat", success.users[0].login)
            }
        }

    @Test
    fun `distinctUntilChanged prevents duplicate work for same query`() = runTest {
        var searchCallCount = 0
        whenever(repository.searchUsers("k")).thenAnswer {
            searchCallCount++
            flowOf(FakeUsersData.getFakeUserEntities(1))
        }

        viewModel.usersUiState.test {
            val initial = awaitItem()
            viewModel.onSearchQueryChanged("k")
            advanceTimeBy(DEBOUNCE_TIMEOUT)
            advanceUntilIdle()
            val first = awaitItem()
            viewModel.onSearchQueryChanged("k")
            advanceTimeBy(DEBOUNCE_TIMEOUT)
            advanceUntilIdle()
            assertEquals(1, searchCallCount)
        }
    }

    @Test
    fun `blank query uses getAllUsers not search`() = runTest {
        whenever(repository.getAllUsers()).thenReturn(flowOf(FakeUsersData.getFakeUserEntities(1)))

        viewModel.usersUiState.test {
            val initial = awaitItem()
            viewModel.onSearchQueryChanged("")
            advanceTimeBy(DEBOUNCE_TIMEOUT)
            advanceUntilIdle()
            verify(repository, times(1)).getAllUsers()
            verify(repository, never()).searchUsers(any())
            val state = awaitItem()
            assert(state is UsersUiState.Success)
        }
    }

    @Test
    fun `mapper fields are propagated to UsersItemUi`() = runTest {
        val e = UserEntity(9, "login9", "https://x/9")
        whenever(repository.getAllUsers()).thenReturn(flowOf(listOf(e)))

        viewModel.usersUiState.test {
            awaitItem()
            advanceTimeBy(DEBOUNCE_TIMEOUT); advanceUntilIdle()
            val s = awaitItem() as UsersUiState.Success
            assertEquals(9, s.users.first().id)
            assertEquals("login9", s.users.first().login)
            assertEquals("https://x/9", s.users.first().avatarUrl)
        }
    }
}

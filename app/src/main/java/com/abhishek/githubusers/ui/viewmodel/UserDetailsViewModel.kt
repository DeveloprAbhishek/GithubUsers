package com.abhishek.githubusers.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.githubusers.UserDetailsActivity.Companion.USER_NAME
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val username: String? = savedStateHandle.get<String>(USER_NAME)

    val uiState: StateFlow<UserDetailsUiState> = combine(
        usersRepository.getUserDetails(username ?: ""),
        usersRepository.getUserRepositories(username ?: "")
    ) { userDetailsResult, reposResult ->
        when {
            userDetailsResult is Result.Success && reposResult is Result.Success -> {
                UserDetailsUiState.Success(userDetailsResult.data, reposResult.data)
            }

            userDetailsResult is Result.Error -> UserDetailsUiState.Error(userDetailsResult.message)
            reposResult is Result.Error -> UserDetailsUiState.Error(reposResult.message)
            else -> UserDetailsUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserDetailsUiState.Loading
    )
}

package com.abhishek.githubusers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.githubusers.data.model.UsersItem
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.utils.AppConstants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {
    private val _usersUiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val usersUiState: StateFlow<UsersUiState> = _usersUiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var allUsers: List<UsersItem> = emptyList()

    init {
        fetchGithubUsers()
    }

    private fun fetchGithubUsers() {
        viewModelScope.launch {
            _usersUiState.value = UsersUiState.Loading
            try {
                val users = usersRepository.getUsersData()
                allUsers = users
                _usersUiState.value = UsersUiState.Success(users)
            } catch (e: Exception) {
                _usersUiState.value = UsersUiState.Error(e.message ?: UNKNOWN_ERROR)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterUsers(query)
    }

    private fun filterUsers(query: String) {
        val filteredList = if (query.isEmpty()) {
            allUsers
        } else {
            allUsers.filter { user ->
                user.login.contains(query, ignoreCase = true)
            }
        }
        _usersUiState.value = UsersUiState.Success(filteredList)
    }
}

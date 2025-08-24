package com.abhishek.githubusers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.utils.AppConstants.DEBOUNCE_TIMEOUT
import com.abhishek.githubusers.utils.AppConstants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val uiMapper: UserUiMapper
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    val usersUiState: StateFlow<UsersUiState> =
        _searchQuery
            .debounce(DEBOUNCE_TIMEOUT)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                val usersFlow = if (query.isBlank()) {
                    usersRepository.getAllUsers()
                } else {
                    usersRepository.searchUsers(query)
                }
                usersFlow.map { entities ->
                    val ui = entities.map { entity -> uiMapper.entityToUi(entity) }
                    if (ui.isNotEmpty()) {
                        UsersUiState.Success(ui)
                    } else {
                        UsersUiState.NoResults("No users found")
                    }
                }
            }
            .catch { e ->
                emit(UsersUiState.Error(e.message ?: UNKNOWN_ERROR))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UsersUiState.Loading
            )

    init {
        refreshUsers()
    }

    private fun refreshUsers() {
        viewModelScope.launch {
            try {
                usersRepository.refreshUsers()
            } catch (e: Exception) {
                // The flow will emit an error state via the catch operator
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}

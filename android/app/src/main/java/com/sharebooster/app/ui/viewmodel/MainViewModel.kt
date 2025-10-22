package com.sharebooster.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharebooster.app.data.local.entity.UserEntity
import com.sharebooster.app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserState(
    val isLoading: Boolean = false,
    val user: UserEntity? = null,
    val error: String? = null
)

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()
    
    fun loadUserProfile() {
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isLoading = true, error = null)
            
            userRepository.getUserProfile()
                .onSuccess { response ->
                    // User will be automatically updated in the database
                    // We can get it from the local database
                    val userId = response.user.userId
                    val user = userRepository.getUserById(userId)
                    _userState.value = UserState(
                        isLoading = false,
                        user = user,
                        error = null
                    )
                }
                .onFailure { error ->
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun updateProfile(
        fullname: String? = null,
        username: String? = null,
        email: String? = null,
        removePfp: Boolean = false
    ) {
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isLoading = true, error = null)
            
            userRepository.updateProfile(fullname, username, email, removePfp)
                .onSuccess { response ->
                    val userId = response.user.userId
                    val user = userRepository.getUserById(userId)
                    _userState.value = UserState(
                        isLoading = false,
                        user = user,
                        error = null
                    )
                }
                .onFailure { error ->
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun changePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ) {
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isLoading = true, error = null)
            
            userRepository.changePassword(currentPassword, newPassword, confirmNewPassword)
                .onSuccess {
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { error ->
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun requestPremium(plan: String) {
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isLoading = true, error = null)
            
            userRepository.requestPremium(plan)
                .onSuccess {
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { error ->
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun clearError() {
        _userState.value = _userState.value.copy(error = null)
    }
}

class MainViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.sharebooster.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharebooster.app.data.model.AuthResponse
import com.sharebooster.app.data.model.User
import com.sharebooster.app.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        checkAuthStatus()
    }
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            authRepository.login(email, password)
                .onSuccess { response ->
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = true,
                        user = response.user,
                        error = null
                    )
                }
                .onFailure { error ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun register(fullname: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            authRepository.register(fullname, username, email, password)
                .onSuccess { response ->
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = true,
                        user = response.user,
                        error = null
                    )
                }
                .onFailure { error ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun googleSignIn(idToken: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            authRepository.googleSignIn(idToken)
                .onSuccess { response ->
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = true,
                        user = response.user,
                        error = null
                    )
                }
                .onFailure { error ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            
            authRepository.logout()
                .onSuccess {
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = false,
                        user = null,
                        error = null
                    )
                }
                .onFailure { error ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
    
    private fun checkAuthStatus() {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true)
            
            authRepository.verifyToken()
                .onSuccess { response ->
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = true,
                        user = response.user,
                        error = null
                    )
                }
                .onFailure {
                    _authState.value = AuthState(
                        isLoading = false,
                        isAuthenticated = false,
                        user = null,
                        error = null
                    )
                }
        }
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
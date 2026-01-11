package com.example.fieldorganizerprov2.auth.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SimpleAuthViewModel: ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun login(account: String, password: String): Boolean {
        // Validate credentials
        val isValid = validateCredentials(account, password)
        if (isValid) {
            _isLoggedIn.value = true
            return true
        }
        return false
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun validateCredentials(account: String, password: String): Boolean {
        var isValid = false
        if(account.trim().equals("Volunteer1")
            && password.trim().equals("123")) {
            isValid = true
        }
        return isValid
    }
}
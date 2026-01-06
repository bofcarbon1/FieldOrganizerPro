package com.example.fieldorganizerprov2.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// 1. Central Navigation State Holder
class AppNavigator @Inject constructor() {
    sealed class Destination {
        object Contacts : Destination()
        object VoterLog : Destination()
        data class ContactDetail(val contactId: String) : Destination()
    }
    private val _currentDestination = MutableStateFlow<Destination>(Destination.Contacts)
    val currentDestination: StateFlow<Destination> = _currentDestination.asStateFlow()

    fun navigateTo(destination: Destination) {
        _currentDestination.value = destination
    }
}

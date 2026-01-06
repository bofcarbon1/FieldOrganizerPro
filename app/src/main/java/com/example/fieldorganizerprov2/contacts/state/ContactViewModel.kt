package com.example.fieldorganizerprov2.contacts.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fieldorganizerprov2.contacts.models.Contact

//@OptIn(ExperimentalPagerApi::class)
class ContactViewModel() : ViewModel() {

    // selected contact data
    var selectedContact by mutableStateOf<Contact?>(null)
        private set

    // selected contact data
    fun onContactLogButtonPressed(contact: Contact) {
        selectedContact = contact
    }

}
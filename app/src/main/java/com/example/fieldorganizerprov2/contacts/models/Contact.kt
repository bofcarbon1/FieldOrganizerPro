package com.example.fieldorganizerprov2.contacts.models

import java.util.UUID

data class Contact(
    val id: UUID?,
    val first_name: String?,
    val last_name: String?,
    val cphone: String?,
    val email: String?,
    val address: String?,
    val city: String?,
    val state: String?,
    val zip_code: String?,
    val notes: String?,
    val status: String?
)

annotation class uuid

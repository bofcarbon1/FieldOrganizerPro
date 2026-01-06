package com.example.fieldorganizerprov2.voter.models

import java.time.LocalDate
import java.util.UUID


// Data model for voter interactions:
data class VoterLogEntry (
    //val id: String = UUID.randomUUID().toString(),
    val voterName: String,           // Links to your Contact
    val dateTime: LocalDate?,   //= LocalDateTime(),now(),
    val contactMethod: ContactMethod, // DOOR, PHONE, EMAIL, TEXT
    val disposition: Disposition,     // NOT_HOME, LEFT_MESSAGE, etc.
    val notes: String                // Free-form notes
    // When to follow up
)

enum class ContactMethod { DOOR_KNOCK, PHONE_CALL, TEXT, EMAIL, SOCIAL_MEDIA }
enum class Disposition {
    NOT_HOME,
    LEFT_MESSAGE,
    SPOKE_WITH_VOTER,
    REFUSED,
    WRONG_ADDRESS,
    DECEASED,
    ALREADY_VOTED,
    NEEDS_RIDE,
    REQUESTED_SIGNAGE
}




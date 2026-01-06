package com.example.fieldorganizerprov2.utilities

//import kotlinx.datetime.LocalDate
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset


// Convert milis to LocalDate (using device timezone for display)
@RequiresApi(Build.VERSION_CODES.O)
fun millisToLocalDate(millis: Long): LocalDate {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

// Convert localDate to millis at start of day in UTC
@RequiresApi(Build.VERSION_CODES.O)
fun localDateToMillis(date: LocalDate): Long {
    return date.atStartOfDay()
        .atZone(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()
}


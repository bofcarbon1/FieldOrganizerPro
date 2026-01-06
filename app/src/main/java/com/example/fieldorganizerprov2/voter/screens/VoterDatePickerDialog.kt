package com.example.fieldorganizerprov2.voter.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.fieldorganizerprov2.utilities.*
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoterDatePickerDialog(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    // Display the selected date
    Text(
        text = selectedDateMillis?.let { millis ->
            // Format the date for display
            SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(millis))
        } ?: "Select Date",
        modifier = Modifier.clickable { showDatePicker = true } // Make the text clickable to show the picker
    )

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateMillis)

        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside or presses cancel
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Update the selected date state and dismiss the dialog
                        selectedDateMillis = datePickerState.selectedDateMillis

                        //Use LocalDate, not millis to avoid zone conflicts
                        //selectedDateMillis?.let { millis ->
                        //    //val selectedDate = millisToLocalDate(millis)
                        onDateSelected(selectedDateMillis)
                        //} //pass back to parent
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            // The actual DatePicker composable
            DatePicker(state = datePickerState)
        }
    }
}

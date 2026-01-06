package com.example.fieldorganizerprov2.voter.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
import com.example.fieldorganizerprov2.ui.theme.default
import com.example.fieldorganizerprov2.ui.theme.redColor
import com.example.fieldorganizerprov2.voter.models.ContactMethod
import com.example.fieldorganizerprov2.voter.models.Disposition
import com.example.fieldorganizerprov2.voter.models.VoterLogEntry
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VoterLogForm(
    navController: NavHostController,
    viewModel: ContactViewModel,
    onSave: ((VoterLogEntry) -> Unit)?,
    onCancel: (() -> Unit)?
) {

    var selectedContactMethod by remember { mutableStateOf(ContactMethod.DOOR_KNOCK) }
    var selectedDisposition by remember { mutableStateOf(Disposition.NOT_HOME) }
    var notes by remember { mutableStateOf("") }
    var followUpDate by remember { mutableStateOf<LocalDate?>(null) }
    var volunteerName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = redColor,
                    titleContentColor = White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().border(
                width = 4.dp,
                color = Color.Red,
                shape = RoundedCornerShape(20.dp)
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            Text(
                "Voter Log Form",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                color = redColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Log Contact: " + viewModel.selectedContact?.first_name + " " +
                        viewModel.selectedContact?.last_name,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
               "Phone: " + viewModel.selectedContact?.cphone,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Contact Method Picker
            Text("Contact Method", style = MaterialTheme.typography.subtitle1)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ContactMethod.values().forEach { method ->
                    FilterChip(
                        selected = selectedContactMethod == method,
                        onClick = { selectedContactMethod = method },
                        label = { Text(method.name.replace("_", " ")) }
                    )
                }
            }

            // Disposition Picker
            Text("Disposition", style = MaterialTheme.typography.subtitle1)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(Disposition.values()) { disposition ->
                    FilterChip(
                        selected = selectedDisposition == disposition,
                        onClick = { selectedDisposition = disposition },
                        label = { Text(disposition.name.replace("_", " ")) }
                    )
                }
            }

            // Notes Field
            TextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4
            )

            // Follow-up Date Picker
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Follow-up Date:", modifier = Modifier.weight(1f))

                if (followUpDate != null) {
                    Text(followUpDate.toString())
                    IconButton(onClick = { followUpDate = null }) {
                        Icon(Icons.Default.Close, "Clear")
                    }
                } else {
                    VoterDatePickerDialog(
                        onDateSelected = { millis ->
                            millis?.let {
                                // Convert millis to LocalDate
                                followUpDate = Instant
                                    .ofEpochMilli(millis)
                                    .atZone(ZoneId.of("GMT"))
                                    .toLocalDate()
                            }
                        },
                        onDismiss = { }
                    )

                }
            }

            // Volunteer Name
            // We call the API using the Volunteer ID and set
            // the volunteer name as display only.

            //Text(
            //    viewModel.selectedContact.value = volunteerName,
            //    onValueChange = { volunteerName = it },
            //    label = { Text("Your Name") },
            //    "xxx"
            //    modifier = Modifier.fillMaxWidth()
            //)
            Text("Volunteer: " + "Brian Quinn", style = MaterialTheme.typography.subtitle1)

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                //if (onCancel != null) {
                //    Button(onClick = onCancel) {
                //        Text("Cancel")
                //    }
                //}

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier
                        .padding(vertical = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = default,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = {
                        //followUpDate = selectedDataMillis
                        val entry = VoterLogEntry(
                            //voterId = viewModel.selectedContact.id?,  // Need to add id to Contact model
                            notes = notes,
                            // volunteer ID will be used to get the name
                            // and set the name. Non editable.
                            dateTime = followUpDate,
                            //id = TODO(),
                            voterName =
                                viewModel.selectedContact?.first_name ?: ("" + " " +
                                        viewModel.selectedContact?.last_name),
                            contactMethod = selectedContactMethod,
                            disposition = selectedDisposition
                            //volunteerName = volunteerName
                        )
                        onSave?.invoke(entry)
                    },
                    //enabled = volunteerName.isNotBlank()
                ) {
                    Text("Save")
                }
            }
        }
    }
}



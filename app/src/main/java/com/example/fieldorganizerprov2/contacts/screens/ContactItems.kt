package com.example.fieldorganizerprov2.contacts.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fieldorganizerprov2.contacts.models.Contact
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
import com.example.fieldorganizerprov2.ui.theme.default
import com.example.fieldorganizerprov2.ui.theme.redColor

    //@OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ContactItem(
        navController: NavHostController,
        contact: Contact,
        viewModel: ContactViewModel) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 4.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = contact.first_name + ' ' + contact.last_name,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = contact.cphone.toString(),
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    //link here to composable VoterLogForm passing contact info
                    Button(
                        modifier = Modifier
                            .padding(vertical = 24.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = default,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp),
                        onClick = {
                            viewModel.onContactLogButtonPressed(contact)
                            // Navigate to voter log with contact ID
                            navController.navigate("voterLog")
                            //navController.navigate("voterLog/${contact.id}")

                        },
                    ) {
                        Text(
                            "Log Contact",
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }

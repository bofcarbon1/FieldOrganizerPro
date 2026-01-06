package com.example.fieldorganizerprov2.home.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fieldorganizerprov2.R
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
import com.example.fieldorganizerprov2.ui.theme.default
import com.example.fieldorganizerprov2.ui.theme.redColor

// TODO add login form
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController,
         viewModel: ContactViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FieldOrganizerPro") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = redColor,
                    titleContentColor = White
                ),
                //navigationIcon = {
                //    IconButton(onClick = { navController.navigateUp() }) {
                //        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                //    }
                //}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxSize()
                .border(
                    width = 4.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            //content = @Composable (ColumnScope.() -> Unit),
        )
        {
            Image(
                painter = painterResource(id = R.drawable.fop_background_images),
                contentDescription = "Republican Party"
            )
            Text(
                "Home",
                style = MaterialTheme.typography.headlineSmall,
                color = redColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
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
                    // Navigate to contact list
                    navController.navigate("contacts")

                },
                //modifier = modifier
            ) {
                Text(
                    "Contact List",
                    color = Color.Black,
                )
            }

        }

    }
}
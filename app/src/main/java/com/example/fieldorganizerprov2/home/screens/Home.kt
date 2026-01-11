package com.example.fieldorganizerprov2.home.screens

import android.R.attr.enabled
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fieldorganizerprov2.R
import com.example.fieldorganizerprov2.auth.viewmodels.SimpleAuthViewModel
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
import com.example.fieldorganizerprov2.ui.theme.default
import com.example.fieldorganizerprov2.ui.theme.redColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController,
         viewModel: ContactViewModel,
         authViewModel: SimpleAuthViewModel = viewModel(),
         onLoginClicked: () -> Unit
) {
    var account by remember { mutableStateOf(" ")}
    var password by remember { mutableStateOf("")}
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FieldOrganizerPro") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = redColor,
                    titleContentColor = White
                ),
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

                if (isLoggedIn)
                {
                    Column(
                        modifier = Modifier
                            //.fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
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
                        //Spacer(modifier = Modifier.height(16.dp))
                        // Place the logout button here
                        Button(
                            modifier = Modifier
                                .padding(vertical = 24.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = default,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(24.dp),
                            onClick = {
                                // Call the logout from
                                authViewModel.logout()
                                //navController.navigate("contacts")

                            },
                            //modifier = modifier
                        ) {
                            Text(
                                "Logout",
                                color = Color.Black,
                            )
                        }
                    }

                } else {
                //login form
                    Column(modifier = Modifier
                        //.fillMaxSize()
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        //horizontalAlignment = Arrangement.Center as Alignment.Horizontal
                        ) {

                        //input field for account
                        OutlinedTextField(
                            value = account,
                            onValueChange = { account = it },
                            label = { Text("Account") },
                            //leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Account Icon") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )

                        Spacer(modifier = Modifier.height(24.dp))


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
                                onLoginClicked()
                                authViewModel.login(account, password)

                            },
                            //modifier = modifier
                        ) {
                            Text(
                                "Login",
                                color = Color.Black,
                            )
                        }
                    }

                }

            }

        }

    }
}
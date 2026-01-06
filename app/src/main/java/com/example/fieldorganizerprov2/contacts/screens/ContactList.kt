package com.example.fieldorganizerprov2.contacts.screens

import android.R.color.white
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.fieldorganizerprov2.contacts.models.Contact
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
import com.example.fieldorganizerprov2.services.ApiService
import com.example.fieldorganizerprov2.ui.theme.redColor
import com.google.accompanist.pager.ExperimentalPagerApi


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun ContactList(
        navController: NavHostController,
        viewModel: ContactViewModel
    ) {
        // Setup for model and api calls for data
        val contacts = remember { mutableStateListOf<Contact>()}
        val apiService = ApiService()

        LaunchedEffect(Unit) {
            val loadedContacts = apiService.getContacts()
            contacts.clear()
            contacts.addAll(loadedContacts)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home") },
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                "Contact List",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                color = redColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            LazyColumn {
                items(contacts) {contact ->
                    ContactItem(
                        navController,
                        contact,
                        viewModel = viewModel,
                        //pagerState = pagerState,
                        //scope = scope,
                    )
                }
            }
        }


        }
    }



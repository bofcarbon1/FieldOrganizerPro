package com.example.fieldorganizerprov2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi

import com.example.fieldorganizerprov2.ui.theme.FieldOrganizerProv2Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fieldorganizerprov2.home.screens.Home
import com.example.fieldorganizerprov2.contacts.screens.ContactList
import com.example.fieldorganizerprov2.contacts.state.ContactViewModel
//import com.example.fieldorganizerprov2.voter.screens.VoterLogForm
//import com.example.fieldorganizerprov2.services.ApiService ..ContactRepository
import com.example.fieldorganizerprov2.ui.theme.redColor
import com.example.fieldorganizerprov2.voter.screens.VoterLogForm
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FieldOrganizerProv2Theme {
                //TabLayout()

                // Start of NavHost
                val viewModel: ContactViewModel = viewModel()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        Home(
                            navController = navController,
                            viewModel = viewModel,
                            onLoginClicked = {},
                        )
                    }
                    composable("contacts") {
                        ContactList(
                            navController = navController,
                            viewModel = viewModel)
                    }
                    composable(
                        //route = "voterLog/{contactId}",
                        route = "voterLog",
                        //arguments = listOf(
                        //    navArgument("contactId") { type = NavType.StringType }
                        //)
                    ) { backStackEntry ->
                        //val contactId = backStackEntry.arguments?.getString("contactId")
                        // Fetch contact from ViewModel or database using contactId
                        VoterLogForm(
                            navController = navController,
                            viewModel = viewModel,
                            onSave = {},
                            onCancel = {}
                        )
                    }
                }
                // end of NavHost
            }
        }
    }
}


// on below line we are creating a
// composable function for our tab layout
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout() {

    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 3)

    // on below line we are creating a column for our widgets.
    Column(
        // for column we are specifying modifier on below line.
        modifier = Modifier.background(Color.White)
    ) {
        // on the below line we are specifying the top app bar
        // and specifying background color for it.
        TopAppBar(backgroundColor = redColor) {
            // on below line we are specifying a column
            // for our text view to display a text
            // in our top app bar.
            Column(
                modifier = Modifier.fillMaxSize(),
                // on below line we are providing alignment for our
                // column to center of our top app bar.
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // on below line we are specifying a text and
                // specifying a text as "Tab Layout Example"
                Text(
                    text = "FieldOrganizer",
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(
                        18F,
                        TextUnitType.Sp
                    ),
                    // on below line we are specifying a modifier
                    // to our text and adding passing from all sides.
                    modifier = Modifier.padding(all = Dp(5F)),
                    // on below line we are aligning
                    // our text to center.
                    textAlign = TextAlign.Center
                )
            }
        }
        // on below line we are calling tabs
        Tabs(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        TabsContent(pagerState = pagerState)
    }
}

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState?) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Home" to Icons.Default.Home,
        "Contacts" to Icons.Default.Person,
        "Log" to Icons.Default.Info
    )
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.


    if (pagerState != null) {
        TabRow(
            // on below line we are specifying
            // the selected index.
            selectedTabIndex = pagerState.currentPage,

            // on below line we are
            // specifying background color.
            backgroundColor = redColor,

            // on below line we are specifying content color.
            contentColor = Color.White,

            // on below line we are specifying
            // the indicator for the tab
            indicator = { tabPositions ->
                // on below line we are specifying the styling
                // for tab indicator by specifying height
                // and color for the tab indicator.
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 2.dp,
                    color = Color.White
                )
            }
        ) {
            // on below line we are specifying icon
            // and text for the individual tab item
            list.forEachIndexed { index, _ ->
                // on below line we are creating a tab.
                Tab(
                    // on below line we are specifying icon
                    // for each tab item and we are calling
                    // image from the list which we have created.
                    icon = {
                        Icon(imageVector = list[index].second, contentDescription = null)
                    },
                    // on below line we are specifying the text for
                    // the each tab item and we are calling data
                    // from the list which we have created.
                    text = {
                        Text(
                            list[index].first,
                            // on below line we are specifying the text color
                            // for the text in that tab
                            color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                        )
                    },
                    // on below line we are specifying
                    // the tab which is selected.
                    selected = pagerState.currentPage == index,
                    // on below line we are specifying the
                    // on click for the tab which is selected.
                    onClick = {
                        // on below line we are specifying the scope.
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}

// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    val navController = rememberNavController()
    //var contact by rememberSaveable { mutableStateOf(Contact) }
    // on below line we are creating {mutableStateof(Contact)}
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {

        // on below line we are specifying
        // the different pages.
            page ->
        //val navController = NavController
        when (page) {

            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> Home(
                navController = navController,
                viewModel = viewModel(),
                onLoginClicked = {}
            ) //TabContentScreen(data = "Welcome to Home Screen")
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> ContactList(
                navController = navController,
                viewModel())
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            // update: 12-30-25 disable anonymous navigation to VoterLogForm
            2 -> VoterLogForm(
                navController = navController,
                viewModel() , //(),
                onSave = null,
                onCancel = null
            ) //TabContentScreen(data = "Welcome to Settings Screen")
        }
    }
}

// on below line we are creating a Tab Content
// Screen for displaying a simple text message.
@Composable
fun TabContentScreen(data: String) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are specifying the text
        Text(
            // on below line we are specifying the text message
            text = data,

            // on below line we are specifying the text style.
            style = MaterialTheme.typography.h5,

            // on below line we are specifying the text color
            color = redColor,

            // on below line we are specifying the font weight
            fontWeight = FontWeight.Bold,

            //on below line we are specifying the text alignment.
            textAlign = TextAlign.Center
        )
    }
}














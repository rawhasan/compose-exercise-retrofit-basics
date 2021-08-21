package com.example.retrifitbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrifitbasics.network.MarsApiFilter
import com.example.retrifitbasics.screens.DetailsScreen
import com.example.retrifitbasics.screens.HomeScreen

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val marsViewModel: MarsViewModel by viewModels()

        setContent {
            RetrofitBasicsApp(marsViewModel)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun RetrofitBasicsApp(marsViewModel: MarsViewModel) {

    val navController = rememberNavController()
    var canPop by remember { mutableStateOf(false) }
    var showDropdownMenu by remember { mutableStateOf(false) }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    var appTitle by remember { mutableStateOf("") }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        canPop = controller.previousBackStackEntry != null
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (canPop) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        } else {
            null
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(appTitle) },
                navigationIcon = navigationIcon,
                actions = {
                    if (showDropdownMenu) { // set from the screens by onShowDropdownMenu event
                        IconButton(onClick = {
                            dropdownMenuExpanded = true
                        }) { // expand the dropdown menu
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Dropdown menu"
                            )

                            // menu must be inside the IconButton to dock in proper position
                            DropdownMenu(
                                expanded = dropdownMenuExpanded,
                                onDismissRequest = { dropdownMenuExpanded = false }) {
                                DropdownMenuItem(onClick = {
                                    marsViewModel.updateFilter(MarsApiFilter.SHOW_ALL)
                                    dropdownMenuExpanded = false
                                }) {
                                    Text("All Properties")
                                }
                                DropdownMenuItem(onClick = {
                                    marsViewModel.updateFilter(MarsApiFilter.SHOW_RENT)
                                    dropdownMenuExpanded = false
                                }) {
                                    Text("Rental Properties")
                                }
                                DropdownMenuItem(onClick = {
                                    marsViewModel.updateFilter(MarsApiFilter.SHOW_BUY)
                                    dropdownMenuExpanded = false
                                }) {
                                    Text("For Sell")
                                }
                            }
                        }

                    }
                }
            )
        },
        content = {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        navController,
                        marsViewModel,
                        onSetTitle = { appTitle = it },
                        onShowDropdownMenu = { showDropdownMenu = it })
                }
                composable("details/{propertyId}") { backStackEntry ->
                    DetailsScreen(
                        backStackEntry.arguments?.getString("propertyId"),
                        marsViewModel,
                        onShowDropdownMenu = { showDropdownMenu = it }
                    )
                }
            }
        }
    )
}


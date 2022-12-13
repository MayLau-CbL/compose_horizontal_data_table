package cbl.tools.compose.horizontaldatatable.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cbl.tools.compose.horizontaldatatable.example.components.DemoCells
import cbl.tools.compose.horizontaldatatable.example.ui.theme.HorizontalDataTableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalDataTableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Example") },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            navController.navigate("home")
                                        }) {
                                        Icon(Icons.Filled.Home, "backIcon")
                                    }
                                }
                            )
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(paddingValues = it)
                        ) {

                            NavHost(
                                navController = navController,
                                startDestination = "home"
                            ) {
                                composable("home") {
                                    HomePage(navController = navController)
                                }
                                composable("simple_table") {
                                    SimpleHorizontalDataTablePage(cells = DemoCells())
                                }
                                composable("refresh_table") {
                                    PullToRefreshHorizontalDataTablePage(cells = DemoCells())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
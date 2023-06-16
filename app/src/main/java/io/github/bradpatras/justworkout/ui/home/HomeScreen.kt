package io.github.bradpatras.justworkout.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Rounded.Home, "home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Rounded.List, "exercises") },
                    label = { Text("Exercises") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Rounded.DateRange, "workouts") },
                    label = { Text("Workouts") }
                )
            }
        }
    ) { paddingValues ->
        Text(text = "Home Screen", modifier = Modifier.padding(paddingValues))
    }
}

//@Composable
//fun BottomBar(
//    navController: NavController
//) {
//    val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
//        ?: NavGraphs.root.startAppDestination
//
//    BottomNavigation {
//        BottomBarDestination.values().forEach { destination ->
//            BottomNavigationItem(
//                selected = currentDestination == destination.direction,
//                onClick = {
//                    navController.navigateTo(destination.direction) {
//                        launchSingleTop = true
//                    }
//                },
//                icon = { Icon(destination.icon, contentDescription = stringResource(destination.label))},
//                label = { Text(stringResource(destination.label)) },
//            )
//        }
//    }
//}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    JustWorkoutTheme() {
        HomeScreen()
    }
}
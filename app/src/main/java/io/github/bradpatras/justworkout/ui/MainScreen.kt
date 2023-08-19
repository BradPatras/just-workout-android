package io.github.bradpatras.justworkout.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.ui.destinations.ExerciseDetailsScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.ExerciseListScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.HomeScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.WorkoutListScreenDestination

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val currentDestination = navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination
    BottomAppBar {
        NavigationBarItem(
            selected = WorkoutListScreenDestination == currentDestination,
            onClick = {
                navController.navigate(
                    WorkoutListScreenDestination
                ) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.Home, "workouts list tab")
            },
            label = {
                Text("Workouts")
            }
        )
        NavigationBarItem(
            selected = ExerciseListScreenDestination == currentDestination,
            onClick = {
                navController.navigate(
                    ExerciseListScreenDestination
                ) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(Icons.Default.List, "exercise list tab")
            },
            label = {
                Text("Exercises")
            }
        )
    }
}
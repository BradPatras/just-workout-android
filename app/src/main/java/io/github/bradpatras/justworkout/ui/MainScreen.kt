package io.github.bradpatras.justworkout.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
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
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.ExerciseListScreenDestination
import com.ramcosta.composedestinations.generated.destinations.WorkoutListScreenDestination
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.startDestination

@Composable
fun MainScreen() {
    val navEngine = rememberNavHostEngine()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        DestinationsNavHost(
            engine = navEngine,
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
    val currentDestination = navController.currentDestinationAsState().value ?: NavGraphs.root.startDestination
    BottomAppBar {
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
                Icon(Icons.AutoMirrored.Filled.List, "exercise list tab")
            },
            label = {
                Text("Exercises")
            }
        )
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
                Icon(Icons.Default.DateRange, "workouts list tab")
            },
            label = {
                Text("Workouts")
            }
        )
    }
}
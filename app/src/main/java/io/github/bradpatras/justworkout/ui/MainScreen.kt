package io.github.bradpatras.justworkout.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.rememberNavHostEngine
import io.github.bradpatras.justworkout.ui.destinations.ExerciseListScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.WorkoutListScreenDestination

@Composable
fun MainScreen() {
    val navEngine = rememberNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        )
    )
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
    val currentDestination = navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination
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
                Icon(Icons.Default.List, "exercise list tab")
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
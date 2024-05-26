package io.github.bradpatras.justworkout.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.ExerciseListScreenDestination
import com.ramcosta.composedestinations.generated.destinations.WorkoutListScreenDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.startDestination

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navEngine = rememberNavHostEngine()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
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
package io.github.bradpatras.justworkout.ui.home

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Destination<RootGraph>
@Composable
fun HomeScreen() {
    Text(text = "Home Screen")
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
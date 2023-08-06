package io.github.bradpatras.justworkout.ui

import android.graphics.drawable.VectorDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import io.github.bradpatras.justworkout.ui.destinations.ExerciseListScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.HomeScreenDestination

enum class BottomAppBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val title: String
) {
    Home(HomeScreenDestination, Icons.Default.Home, "Home"),
    ExerciseList(ExerciseListScreenDestination, Icons.Default.List, "Exercises")
}
package io.github.bradpatras.justworkout.ui.exercises.select

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Composable
@Destination<RootGraph>(navArgs = ExerciseSelectScreenNavArgs::class)
fun ExerciseSelectScreen(
    viewModel: ExerciseSelectViewModel,
    backNavigator: ResultBackNavigator<ExerciseSelectScreenNavArgs>
) {
    Text("hi")
}
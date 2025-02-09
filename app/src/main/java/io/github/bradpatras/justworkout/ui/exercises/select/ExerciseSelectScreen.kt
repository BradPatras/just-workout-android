@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.bradpatras.justworkout.ui.exercises.select

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.result.ResultBackNavigator
import io.github.bradpatras.justworkout.models.DefaultModels
import io.github.bradpatras.justworkout.models.SelectableExercise
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Composable
@Destination<RootGraph>(navArgs = ExerciseSelectScreenNavArgs::class)
fun ExerciseSelectScreen(
    viewModel: ExerciseSelectViewModel = hiltViewModel(),
    backNavigator: ResultBackNavigator<ExerciseSelectScreenNavArgs>
) {
    val state = viewModel.uiState.collectAsState()

    ExerciseSelectContent(
        state = state.value,
        onExerciseTapped = { viewModel.exerciseTapped(it) },
        onCheckmarkTapped = {
            backNavigator.navigateBack(
                result = ExerciseSelectScreenNavArgs(
                    state.value.exercises.filter { it.isSelected }.map { it.exercise }.toTypedArray()
                )
            )
        },
        onCloseTapped = { backNavigator.navigateBack() }
    )
}

@Composable
private fun ExerciseSelectContent(
    state: ExerciseSelectUiState,
    onExerciseTapped: (SelectableExercise) -> Unit,
    onCheckmarkTapped: () -> Unit,
    onCloseTapped: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Select exercises"
                )
            },
            navigationIcon = {
                IconButton(onClick = { onCloseTapped() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                IconButton(onClick = { onCheckmarkTapped() }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized description"
                    )
                }
            },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        )

        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = state.exercises,
                key = { it.id() }
            ) { exercise ->
                Row() {
                    ExerciseListItem(
                        exercise,
                        onCheckboxChanged = {
                            onExerciseTapped(exercise)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExerciseListItem(
    exercise: SelectableExercise,
    onCheckboxChanged: (Boolean) -> Unit
) {
    Column {
        ListItem(
            headlineContent = {
                Text(
                    text = exercise.title(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                if (exercise.tags().isNotEmpty()) {
                    Text(
                        text = exercise.tags().joinToString { it.title },
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            trailingContent = {
                Checkbox(exercise.isSelected, onCheckedChange = onCheckboxChanged)
            }
        )
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExerciseSelectPreview() {
    JustWorkoutTheme {
        ExerciseSelectContent(
            state = ExerciseSelectUiState(
                isLoading = false,
                exercises = DefaultModels.exercises.map { SelectableExercise(it, false) }
            ),
            onExerciseTapped = {},
            onCheckmarkTapped = {},
            onCloseTapped = {}
        )
    }
}
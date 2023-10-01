@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.ui.destinations.ExerciseDetailsScreenDestination
import io.github.bradpatras.justworkout.ui.destinations.ExerciseEditScreenDestination
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import java.util.UUID

@Destination
@Composable
fun ExerciseListScreen(
    viewModel: ExerciseListViewModel = hiltViewModel(),
    destinationsNavigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()

    ExerciseListContent(
        uiState = uiState,
        onAddButtonClick = {
            destinationsNavigator.navigate(
                ExerciseEditScreenDestination(id = UUID.randomUUID(), isNew = true)
            )
        },
        onItemClick = { exercise ->
            destinationsNavigator.navigate(
                ExerciseDetailsScreenDestination(exercise = exercise)
            )
        }
    )
}

@Composable
fun ExerciseListContent(
    uiState: ExerciseListUiState,
    onAddButtonClick: () -> Unit,
    onItemClick: (Exercise) -> Unit
) {
    Column {
        TopAppBar(
            title = { Text("Exercises") },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        LazyColumn(
            contentPadding = PaddingValues(12.dp)
        ) {
            items(uiState.exercises) { exercise ->
                Surface(
                    Modifier.clickable {
                        onItemClick(exercise)
                    }
                ) {
                    ExerciseListItem(exercise = exercise)
                }
            }
        }
    }

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onAddButtonClick() },
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Add, "add exercise")
        }
    }
}

@Composable
private fun ExerciseListItem(exercise: Exercise) {
    Column {
        ListItem(
            headlineContent = {
                Text(text = exercise.title)
            },
            supportingContent = {
                Text(text = exercise.tags.joinToString { it.title })
            }
        )
        Divider()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExerciseListPreview() {
    JustWorkoutTheme {
        ExerciseListContent(
            uiState = ExerciseListUiState(),
            onAddButtonClick = { },
            onItemClick = { }
        )
    }
}


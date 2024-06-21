package io.github.bradpatras.justworkout.ui.workouts.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Destination<RootGraph>
@Composable
fun WorkoutListScreen(
    viewModel: WorkoutListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    WorkoutListContent(
        uiState = uiState,
        onItemClick = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListContent(
    uiState: WorkoutListUiState,
    onItemClick: (Workout) -> Unit
) {
    Column {
        TopAppBar(
            title = { Text("Workouts") },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(12.dp)
            ) {
                items(
                    items = uiState.workouts,
                    key = { it.id }
                ) { workout ->
                    Surface(
                        Modifier.clickable {
                            onItemClick(workout)
                        }
                    ) {
                        WorkoutListItem(workout = workout)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { },
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Add, "add workout")
        }
    }
}

@Composable
private fun WorkoutListItem(workout: Workout) {
    Column {
        ListItem(
            headlineContent = {
                Text(text = workout.title)
            },
            supportingContent = {
                if (workout.exercises.isEmpty()) {
                    Text(text = "No exercises added")
                } else {
                    Text(text = workout.exercises.joinToString { it.title })
                }
            }
        )
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WorkoutListPreview() {
    JustWorkoutTheme {
        WorkoutListContent(
            uiState = WorkoutListUiState(
                isLoading = false,
                Mocks.mockWorkoutList
            )
        ) { }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.bradpatras.justworkout.ui.exercises.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
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
import com.ramcosta.composedestinations.annotation.Destination
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import androidx.lifecycle.viewmodel.compose.viewModel
@Destination
@Composable
fun ExerciseListScreen(
    viewModel: ExerciseListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ExerciseListContent(
        uiState = uiState
    )
}

@Composable
fun ExerciseListContent(
    uiState: ExerciseListUiState
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
                ExerciseListItem(exercise = exercise)
            }
        }
    }

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { }, //onAddButtonTapped() },
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Add, "add exercise")
        }
    }
}

@Composable
private fun ExerciseListItem(exercise: Exercise) {
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

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseListPreview() {
    JustWorkoutTheme() {
        ExerciseListContent(
            uiState = ExerciseListUiState(
                exercises = listOf(
                    Exercise(
                        description = "this is the description",
                        id = 0,
                        muscleGroups = emptyList(),
                        tags = listOf(
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Strength"

                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Chest"

                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Arms"

                            )
                        ),
                        title = "Bench press"

                    ),
                    Exercise(
                        description = "this is the description",
                        id = 1,
                        muscleGroups = emptyList(),
                        tags = listOf(
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Mobility"
                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Warmup"

                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Arms"
                            )
                        ),
                        title = "Shoulder circles"
                    ),
                    Exercise(
                        description = "this is the description",
                        id = 2,
                        muscleGroups = emptyList(),
                        tags = listOf(
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Strength"
                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Arms"
                            )
                        ),
                        title = "Bicep curls"

                    ),
                    Exercise(
                        description = "this is the description",
                        id = 3,
                        muscleGroups = emptyList(),
                        tags = listOf(
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Strength"
                            ),
                            Tag(
                                color = Color.BLUE,
                                id = 0,
                                title = "Chest"
                            )
                        ),
                        title = "Chest cable flys"
                    )
                )
            )
        )
    }
}


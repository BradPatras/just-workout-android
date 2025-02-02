package io.github.bradpatras.justworkout.ui.workouts.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ExerciseDetailsScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ExerciseEditScreenDestination
import com.ramcosta.composedestinations.generated.destinations.WorkoutEditScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.result.onResult
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.R
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.ui.exercises.edit.ExerciseEditScreenNavArgs
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import java.util.Date
import java.util.UUID

@Destination<RootGraph>(navArgs = WorkoutDetailsScreenNavArgs::class)
@Composable
fun WorkoutDetailsScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: WorkoutDetailsViewModel = hiltViewModel(),
//    resultRecipient: ResultRecipient<WorkoutEditScreenDestination, WorkoutEditScreenNavArgs>
) {
    val uiState = viewModel.uiState.collectAsState()

//    resultRecipient.onResult {
//        viewModel.reloadWorkout()
//    }

    WorkoutDetailsContent(
        uiState = uiState.value,
        destinationsNavigator = destinationsNavigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailsContent(
    uiState: WorkoutDetailsUiState,
    destinationsNavigator: DestinationsNavigator
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopAppBar(
            title = { Text(text = "Workout Details") },
            navigationIcon = {
                IconButton(onClick = { destinationsNavigator.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        destinationsNavigator.navigate(
                            WorkoutEditScreenDestination(
                                id = uiState.workout.id,
                                isNew = false
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit workout"
                    )
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = uiState.workout.title,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (uiState.workout.tags.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_tag),
                        contentDescription = "Tags icon",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = uiState.workout.tags.joinToString(", ") { it.title },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            if (uiState.workout.notes.isNotBlank()) {
                Text(
                    text = uiState.workout.notes,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (uiState.workout.datesCompleted.isNotEmpty()) {
                Text(
                    text = "Last completed: ${uiState.workout.datesCompleted.last().toString()}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (uiState.workout.exercises.isNotEmpty()) {
//                HorizontalDivider()

                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                    Text(
//                        text = "Exercises",
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = MaterialTheme.colorScheme.onSurface,
//                    )

                    uiState.workout.exercises.forEach { exercise ->
                        Box(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(
                                        1.dp,
                                        MaterialTheme.colorScheme.outlineVariant
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    destinationsNavigator.navigate(
                                        ExerciseDetailsScreenDestination(
                                            id = exercise.id
                                        )
                                    )
                                }
                                .padding(vertical = 4.dp, horizontal = 12.dp)

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(vertical = 6.dp)
                            ) {
                                Text(
                                    text = exercise.title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                        .weight(1f),

                                )

                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowRight,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WorkoutDetailsPreview() {
    JustWorkoutTheme {
        WorkoutDetailsContent(
            uiState = WorkoutDetailsUiState(
                workout = Workout(
                    notes = "This is the notes, this is where you can give some extra info about this workout",
                    id = UUID.randomUUID(),
                    tags = listOf(
                        Tag(
                            tagId = UUID.randomUUID(),
                            title = "Tricep"
                        ),
                        Tag(
                            tagId = UUID.randomUUID(),
                            title = "Chest"
                        )
                    ),
                    datesCompleted = listOf(Date()),
                    exercises = Mocks.mockExerciseList,
                    title = "Push Workout"
                )
            ),
            destinationsNavigator = EmptyDestinationsNavigator
        )
    }
}
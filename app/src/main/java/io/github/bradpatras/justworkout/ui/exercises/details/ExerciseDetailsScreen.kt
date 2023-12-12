package io.github.bradpatras.justworkout.ui.exercises.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.composables.TagChip
import io.github.bradpatras.justworkout.ui.destinations.ExerciseEditScreenDestination
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import java.util.UUID

@Destination(navArgsDelegate = ExerciseDetailsUiState::class)
@Composable
fun ExerciseDetailsScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: ExerciseDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ExerciseDetailsContent(
        uiState = uiState.value,
        destinationsNavigator = destinationsNavigator
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ExerciseDetailsContent(
    uiState: ExerciseDetailsUiState,
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
            title = { Text(text = "Exercise Details") },
            navigationIcon = {
                IconButton(onClick = { destinationsNavigator.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        destinationsNavigator.navigate(
                            ExerciseEditScreenDestination(
                                id = uiState.exercise.id,
                                isNew = false
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit exercise button"
                    )
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {

            Text(
                text = uiState.exercise.title,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )

            FlowRow(
                modifier = Modifier.align(Alignment.Start)
            ) {
                uiState.exercise.tags.forEach {
                    TagChip(title = it.title)
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )

            Text(
                text = uiState.exercise.description,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseDetailsPreview() {
    JustWorkoutTheme() {
        ExerciseDetailsContent(
            uiState = ExerciseDetailsUiState(
                exercise = Exercise(
                    description = "Elbows in, shoulder blades pinched, don't bounce the bar off your chest",
                    id = UUID.randomUUID(),
                    tags = listOf(
                        Tag(
                            title = "Tricep"
                        ),
                        Tag(
                            title = "Chest"
                        )
                    ),
                    title = "Bench press test a really long title that will not fit in the topbar"
                )
            ),
            destinationsNavigator = EmptyDestinationsNavigator
        )
    }
}
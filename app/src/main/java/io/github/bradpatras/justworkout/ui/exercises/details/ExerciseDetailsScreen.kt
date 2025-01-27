package io.github.bradpatras.justworkout.ui.exercises.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ExerciseEditScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import io.github.bradpatras.justworkout.R
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import java.util.UUID

@Destination<RootGraph>(navArgs = ExerciseDetailsScreenNavArgs::class)
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

@OptIn(ExperimentalMaterial3Api::class)
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = uiState.exercise.title,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (uiState.exercise.tags.isNotEmpty()) {
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
                        text = uiState.exercise.tags.joinToString(", ") { it.title },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            if (uiState.exercise.description.isNotBlank()) {
                Text(
                    text = uiState.exercise.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseDetailsPreview() {
    JustWorkoutTheme {
        ExerciseDetailsContent(
            uiState = ExerciseDetailsUiState(
                exercise = Exercise(
                    description = "This is the description, this is where you can give some extra info about this exercise",
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
                    title = "Bench Press"
                )
            ),
            destinationsNavigator = EmptyDestinationsNavigator
        )
    }
}
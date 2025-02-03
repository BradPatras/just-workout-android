@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.bradpatras.justworkout.ui.workouts.edit

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.TagsSelectScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.result.onResult
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.models.DefaultModels
import io.github.bradpatras.justworkout.ui.exercises.edit.ExerciseEditContent
import io.github.bradpatras.justworkout.ui.exercises.edit.ExerciseEditUiState
import io.github.bradpatras.justworkout.ui.tags.TagsSelectScreenNavArgs
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import java.util.UUID

@Destination<RootGraph>(navArgs = WorkoutEditScreenNavArgs::class)
@Composable
fun WorkoutEditScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkoutEditViewModel = hiltViewModel(),
    tagSelectResultRecipient: ResultRecipient<TagsSelectScreenDestination, TagsSelectScreenNavArgs>,
    //exerciseSelectResultRecipient: ResultRecipient<TagsSelectScreenDestination, TagsSelectScreenNavArgs>,
) {
    val uiState = viewModel.uiState.collectAsState()

    tagSelectResultRecipient.onResult {
        viewModel.onTagsChanged(it.selectedTags.toList())
    }

//    exerciseSelectResultRecipient.onResult {
//        viewModel.onExercisesChanged(emptyList())
//    }

    WorkoutEditContent(
        uiState = uiState.value,
        onTitleChanged = { viewModel.onTitleChanged(it) },
        onNotesChanged = { viewModel.onNotesChanged(it) },
        onCheckmarkTapped = {
            viewModel.onCheckmarkTapped()
            navigator.popBackStack()
        },
        destinationsNavigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutEditContent(
    uiState: WorkoutEditUiState,
    onTitleChanged: (String) -> Unit,
    onNotesChanged: (String) -> Unit,
    onCheckmarkTapped: () -> Unit,
    destinationsNavigator: DestinationsNavigator
) {
    if(!uiState.isLoading) {
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
                        text = if (uiState.isNew) "Create workout" else "Edit workout"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { destinationsNavigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cancel"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onCheckmarkTapped()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Save"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = uiState.title,
                    label = { Text("Title") },
                    onValueChange = { onTitleChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.notes,
                    label = { Text("Notes") },
                    onValueChange = { onNotesChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    minLines = 3,
                )

                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    OutlinedTextField(
                        value = uiState.tags.joinToString { it.title },
                        label = { Text("Tags") },
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusable(false),
                        readOnly = true,
                    )

                    Box(modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            destinationsNavigator.navigate(
                                TagsSelectScreenDestination(selectedTags = uiState.tags.toTypedArray())
                            )
                        }
                    )
                }

                OutlinedTextField(
                    value = uiState.notes,
                    label = { Text(uiState.exercises.joinToString { it.title }) },
                    onValueChange = { onNotesChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    minLines = 3,
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WorkoutEditPreview() {
    JustWorkoutTheme {
        WorkoutEditContent(
            uiState = WorkoutEditUiState(
                id = UUID.randomUUID(),
                notes = "These are some notes about this workout",
                isLoading = false,
                tags = Mocks.mockTagList1,
                title = "This is the title",
                isNew = false,
                exercises = listOf(DefaultModels.Exercises.CHEST_FLY.exercise, DefaultModels.Exercises.BENCH_PRESS.exercise)
            ),
            { },
            { },
            { },
            destinationsNavigator = EmptyDestinationsNavigator
        )
    }
}
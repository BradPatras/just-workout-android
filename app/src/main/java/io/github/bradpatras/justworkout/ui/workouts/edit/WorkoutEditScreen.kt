@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.bradpatras.justworkout.ui.workouts.edit

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.TagsSelectScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.result.onResult
import io.github.bradpatras.justworkout.ui.tags.TagsSelectScreenNavArgs

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
}

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
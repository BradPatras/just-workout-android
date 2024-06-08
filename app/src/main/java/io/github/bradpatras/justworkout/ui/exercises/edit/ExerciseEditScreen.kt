package io.github.bradpatras.justworkout.ui.exercises.edit

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.TagsSelectScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Destination<RootGraph>(navArgs = ExerciseEditScreenNavArgs::class)
@Composable
fun ExerciseEditScreen(
    navigator: DestinationsNavigator,
    viewModel: ExerciseEditViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ExerciseEditContent(
        uiState = uiState.value,
        onTitleChanged = { viewModel.onTitleChanged(it) },
        onDescriptionChanged = { viewModel.onDescriptionChanged(it) },
        onCheckmarkTapped = {
            viewModel.onCheckmarkTapped()
            navigator.popBackStack()
        },
        onTagsSelectionChanged = {
            viewModel.onTagsSelectionChanged(it)
        },
        destinationsNavigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseEditContent(
    uiState: ExerciseEditUiState,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onCheckmarkTapped: () -> Unit,
    onTagsSelectionChanged: (List<Tag>) -> Unit,
    destinationsNavigator: DestinationsNavigator
) {
    if (!uiState.isLoading) {
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
                        text = if (uiState.isNew) "Create exercise" else "Edit exercise"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { destinationsNavigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Localized description"
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
                            contentDescription = "Localized description"
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
                    value = uiState.description,
                    label = { Text("Description") },
                    onValueChange = { onDescriptionChanged(it) },
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
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExerciseEditPreview() {
    JustWorkoutTheme {
        ExerciseEditContent(
            uiState = ExerciseEditUiState(
                description = "This is the description of the exercise",
                isLoading = false,
                tags = Mocks.mockTagList1,
                title = "This is the title",
                isNew = false
            ),
            { },
            { },
            { },
            { },
            destinationsNavigator = EmptyDestinationsNavigator
        )
    }
}
package io.github.bradpatras.justworkout.ui.exercises.edit

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.composables.TagChip
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@Destination(navArgsDelegate = ExerciseEditScreenNavArgs::class)
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
        onDeleteTagTapped = { viewModel.onDeleteTagTapped(it) },
        destinationsNavigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ExerciseEditContent(
    uiState: ExerciseEditUiState,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onCheckmarkTapped: () -> Unit,
    onDeleteTagTapped: (Tag) -> Unit,
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

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                uiState.tags.forEach {
                    TagChip(
                        title = it.title,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                "remove tag button",
                                tint = MaterialTheme.colorScheme.error
                            )
                        },
                        onClick = {
                            onDeleteTagTapped(it)
                        }
                    )
                }

                TagChip(
                    title = "Add Tag",
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            "add tag button"
                        )
                    },
                    onClick = {
                        // Show Tag select screen
                    }
                )
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
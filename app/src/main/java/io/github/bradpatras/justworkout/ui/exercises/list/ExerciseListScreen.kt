@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package io.github.bradpatras.justworkout.ui.exercises.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ExerciseDetailsScreenDestination
import com.ramcosta.composedestinations.generated.destinations.ExerciseEditScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.bradpatras.justworkout.Mocks
import io.github.bradpatras.justworkout.R
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.composables.TagChip
import io.github.bradpatras.justworkout.ui.tags.TagsSelectContent
import io.github.bradpatras.justworkout.ui.tags.TagsSelectUiState
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import kotlinx.coroutines.launch

@Destination<RootGraph>(start = true)
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
                ExerciseEditScreenDestination(id = viewModel.uuidProvider.random(), isNew = true)
            )
        },
        onItemClick = { exercise ->
            destinationsNavigator.navigate(
                ExerciseDetailsScreenDestination(exercise.id)
            )
        },
        onTagFilterSelected = { viewModel.onTagFilterSelected(it) }
    )
}

@Composable
fun ExerciseListContent(
    uiState: ExerciseListUiState,
    onAddButtonClick: () -> Unit,
    onItemClick: (Exercise) -> Unit,
    onTagFilterSelected: (List<Tag>) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = { Text("Exercises") },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            actions = {
                IconButton(
                    onClick = { showBottomSheet = true },
                ) {
                    BadgedBox(badge = {
                        if (uiState.tagFilter.isNotEmpty()) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.padding(1.dp)
                            )
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_list) ,
                            contentDescription = "Localized description"
                        )
                    }
                }
            },
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
                contentPadding = PaddingValues(8.dp),
            ) {
                items(
                    items = uiState.exercises,
                    key = { it.id }
                ) { exercise ->
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
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onAddButtonClick() },
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Add, "add exercise")
        }
    }

    if (showBottomSheet) {
        TagFilterBottomSheet(
            tags = uiState.tags,
            selectedTags = uiState.tagFilter,
            onDismissRequest = { showBottomSheet = false },
            onFiltersApplied = { onTagFilterSelected(it) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagFilterBottomSheet(
    tags: List<Tag>,
    selectedTags: List<Tag>,
    onDismissRequest: () -> Unit,
    onFiltersApplied: (List<Tag>) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var tagSelection by remember { mutableStateOf(selectedTags) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Filter", style = MaterialTheme.typography.headlineSmall)

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                tags.forEach {
                    TagChip(
                        title = it.title,
                        onClick = {
                            tagSelection = tagSelection.toMutableList().apply {
                                if (contains(it)) {
                                    remove(it)
                                } else {
                                    add(it)
                                }
                            }
                        },
                        selected = tagSelection.contains(it)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = { tagSelection = emptyList() }) {
                    Text(text = "Reset", modifier = Modifier.padding(horizontal = 16.dp))

                }

                Button(onClick = {
                    onFiltersApplied(tagSelection)
                    onDismissRequest()
                }) {
                    Text(text = "Apply (${tagSelection.size})", modifier = Modifier.padding(horizontal = 16.dp))
                }
            }

            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
private fun ExerciseListItem(exercise: Exercise) {
    Column {
        ListItem(
            headlineContent = {
                Text(
                    text = exercise.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                if (exercise.tags.isNotEmpty()) {
                    Text(
                        text = exercise.tags.joinToString { it.title },
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseListPreview() {
    JustWorkoutTheme {
        ExerciseListContent(
            uiState = ExerciseListUiState(
                exercises = Mocks.mockExerciseList
            ),
            onAddButtonClick = { },
            onItemClick = { },
            onTagFilterSelected = { }
        )
    }
}


@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package io.github.bradpatras.justworkout.ui.exercises.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import io.github.bradpatras.justworkout.ui.composables.TagFilterBottomSheet
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

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
                                containerColor = MaterialTheme.colorScheme.onSecondary
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


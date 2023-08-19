package io.github.bradpatras.justworkout.ui.exercises.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.MuscleGroup
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Destination
@Composable
fun ExerciseDetailsScreen(
    exercise: Exercise
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        LargeTopAppBar(
            title = { Text(text = exercise.title) },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            FlowRow(
                modifier = Modifier.align(Alignment.Start)
            ) {
                exercise.muscleGroups.forEach {
                    MuscleGroupChip(muscleGroup = it)
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )

            Text(
                text = exercise.description,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        FlowRow(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            exercise.tags.forEach {
                TagChip(tag = it)
            }
        }
    }
}

@Composable
fun MuscleGroupChip(muscleGroup: MuscleGroup) {
    AssistChip(
        onClick = {},
        label = {
            Text(
                text = muscleGroup.title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        },
        modifier = Modifier.padding(horizontal = 4.dp),
        border = null,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}

@Composable
fun TagChip(tag: Tag) {
    AssistChip(
        onClick = {},
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = Color.Transparent
        ),
        label = {
            Text(
                text = tag.title,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .horizontalScroll(
                        state = ScrollState(0),
                        enabled = true
                    ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        shape = CircleShape,
        modifier = Modifier.padding(horizontal = 4.dp),
        leadingIcon = {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(tag.color))
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseDetailsPreview() {
    JustWorkoutTheme() {
        ExerciseDetailsScreen(
            exercise = Exercise(
                description = "Elbows in, shoulder blades pinched, don't bounce the bar off your chest",
                id = 0,
                muscleGroups = listOf(
                    MuscleGroup(
                        id = 0,
                        title = "Tricep"
                    ),
                    MuscleGroup(
                        id = 1,
                        title = "Chest"
                    )
                ),
                tags = listOf(
                    Tag(
                        color = Color.Blue.hashCode(),
                        id = 0,
                        title = "Strength"
                    ),
                    Tag(
                        color = Color.Green.hashCode(),
                        id = 1,
                        title = "Push"
                    )
                ),
                title = "Bench press"
            )
        )
    }
}
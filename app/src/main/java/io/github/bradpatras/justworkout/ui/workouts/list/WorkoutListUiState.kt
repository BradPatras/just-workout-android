package io.github.bradpatras.justworkout.ui.workouts.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date
import java.util.UUID

data class WorkoutListUiState(
    val workouts: List<Workout> = listOf(
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = UUID.randomUUID(),
                    tags = emptyList(),
                    title = "Bicep curls"

                ),
                Exercise(
                    description = "this is the description",
                    id = UUID.randomUUID(),
                    tags = emptyList(),
                    title = "Seated Rows"

                )
            ),
            id = UUID.randomUUID(),
            notes = "these are the notes",
            title = "Basic Pull Workout"
        ),
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = UUID.randomUUID(),
                    tags = emptyList(),
                    title = "Lat raises"

                ), Exercise(
                    description = "this is the description",
                    id = UUID.randomUUID(),
                    tags = emptyList(),
                    title = "Bench press"

                )
            ),
            id = UUID.randomUUID(),
            notes = "these are the notes",
            title = "Basic Push Workout"
        )
    )
)
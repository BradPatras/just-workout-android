package io.github.bradpatras.justworkout.ui.workouts.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date

data class WorkoutListUiState(
    val workouts: List<Workout> = listOf(
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = 2,
                    tags = emptyList(),
                    title = "Bicep curls"

                ),
                Exercise(
                    description = "this is the description",
                    id = 2,
                    tags = emptyList(),
                    title = "Seated Rows"

                )
            ),
            id = 0,
            notes = "these are the notes",
            title = "Basic Pull Workout"
        ),
        Workout(
            datesCompleted = listOf(Date()),
            exercises = listOf(
                Exercise(
                    description = "this is the description",
                    id = 2,
                    tags = emptyList(),
                    title = "Lat raises"

                ), Exercise(
                    description = "this is the description",
                    id = 2,
                    tags = emptyList(),
                    title = "Bench press"

                )
            ),
            id = 0,
            notes = "these are the notes",
            title = "Basic Push Workout"
        )
    )
)
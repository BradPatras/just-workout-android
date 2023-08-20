package io.github.bradpatras.justworkout.ui.exercises.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag

data class ExerciseListUiState(
    val exercises: List<Exercise> = listOf(
        Exercise(
            description = "this is the description",
            id = 0,
            tags = listOf(
                Tag(
                    id = 0,
                    title = "Strength"

                ),
                Tag(
                    id = 0,
                    title = "Chest"

                ),
                Tag(
                    id = 0,
                    title = "Arms"

                )
            ),
            title = "Bench pressBench pressBench pressBench pressBench press"

        ),
        Exercise(
            description = "this is the description",
            id = 1,
            tags = listOf(
                Tag(
                    id = 0,
                    title = "Mobility"
                ),
                Tag(
                    id = 0,
                    title = "Warmup"

                ),
                Tag(
                    id = 0,
                    title = "Arms"
                )
            ),
            title = "Shoulder circles"
        ),
        Exercise(
            description = "this is the description",
            id = 2,
            tags = listOf(
                Tag(
                    id = 0,
                    title = "Strength"
                ),
                Tag(
                    id = 0,
                    title = "Arms"
                )
            ),
            title = "Bicep curls"

        ),
        Exercise(
            description = "this is the description",
            id = 3,
            tags = listOf(
                Tag(
                    id = 0,
                    title = "Strength"
                ),
                Tag(
                    id = 0,
                    title = "Chest"
                )
            ),
            title = "Chest cable flys"
        )
    )
)

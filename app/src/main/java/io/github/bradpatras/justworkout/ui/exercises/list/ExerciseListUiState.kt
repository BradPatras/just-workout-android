package io.github.bradpatras.justworkout.ui.exercises.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

data class ExerciseListUiState(
    val exercises: List<Exercise> = listOf(
        Exercise(
            description = "this is the description",
            id = UUID.randomUUID(),
            tags = listOf(
                Tag(
                    id = UUID.randomUUID(),
                    title = "Strength"

                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Chest"

                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Arms"

                )
            ),
            title = "Bench pressBench pressBench pressBench pressBench press"

        ),
        Exercise(
            description = "this is the description",
            id = UUID.randomUUID(),
            tags = listOf(
                Tag(
                    id = UUID.randomUUID(),
                    title = "Mobility"
                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Warmup"

                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Arms"
                )
            ),
            title = "Shoulder circles"
        ),
        Exercise(
            description = "this is the description",
            id = UUID.randomUUID(),
            tags = listOf(
                Tag(
                    id = UUID.randomUUID(),
                    title = "Strength"
                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Arms"
                )
            ),
            title = "Bicep curls"

        ),
        Exercise(
            description = "this is the description",
            id = UUID.randomUUID(),
            tags = listOf(
                Tag(
                    id = UUID.randomUUID(),
                    title = "Strength"
                ),
                Tag(
                    id = UUID.randomUUID(),
                    title = "Chest"
                )
            ),
            title = "Chest cable flys"
        )
    )
)

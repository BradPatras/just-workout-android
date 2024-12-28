package io.github.bradpatras.justworkout.ui.exercises.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

data class ExerciseListUiState(
    val exercises: List<Exercise> = emptyList(),
    val tagFilter: List<Tag> = emptyList(),
    val isLoading: Boolean = false
)

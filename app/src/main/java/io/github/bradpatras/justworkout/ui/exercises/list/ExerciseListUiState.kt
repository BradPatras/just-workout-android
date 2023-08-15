package io.github.bradpatras.justworkout.ui.exercises.list

import io.github.bradpatras.justworkout.models.Exercise

data class ExerciseListUiState(
    val exercises: List<Exercise> = emptyList()
)

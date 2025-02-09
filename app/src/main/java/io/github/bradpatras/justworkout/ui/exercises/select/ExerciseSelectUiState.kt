package io.github.bradpatras.justworkout.ui.exercises.select

import io.github.bradpatras.justworkout.models.Exercise

data class ExerciseSelectUiState(
    val isLoading: Boolean,
    val allExercises: List<Exercise>,
    val selectedExercises: List<Exercise>
)
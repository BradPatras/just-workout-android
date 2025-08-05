package io.github.bradpatras.justworkout.ui.exercises.select

import io.github.bradpatras.justworkout.models.SelectableExercise

data class ExerciseSelectUiState(
    val isLoading: Boolean,
    val exercises: List<SelectableExercise>,
    val searchQuery: String,
)
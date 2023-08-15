package io.github.bradpatras.justworkout.ui.workouts.list

import io.github.bradpatras.justworkout.models.Workout

data class WorkoutListUiState(
    val workouts: List<Workout> = emptyList()
)
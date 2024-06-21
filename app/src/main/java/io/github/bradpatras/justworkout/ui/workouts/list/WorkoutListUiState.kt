package io.github.bradpatras.justworkout.ui.workouts.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date
import java.util.UUID

data class WorkoutListUiState(
    val isLoading: Boolean,
    val workouts: List<Workout> = emptyList()
)
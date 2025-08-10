package io.github.bradpatras.justworkout.ui.workouts.list

import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.models.Workout

data class WorkoutListUiState(
    val isLoading: Boolean,
    val tagFilter: List<Tag> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val workouts: List<Pair<Workout, Boolean>> = emptyList(),
    val isSelectMode: Boolean = false,
)
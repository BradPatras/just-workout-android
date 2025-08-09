package io.github.bradpatras.justworkout.ui.exercises.list

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.SelectableExercise
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

data class ExerciseListUiState(
    val exercises: List<SelectableExercise> = emptyList(),
    val tagFilter: List<Tag> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val isLoading: Boolean = false,
    val isSelectMode: Boolean = false,
)

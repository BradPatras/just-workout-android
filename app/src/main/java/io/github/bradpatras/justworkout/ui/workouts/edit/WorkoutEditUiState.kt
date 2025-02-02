package io.github.bradpatras.justworkout.ui.workouts.edit

import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

data class WorkoutEditUiState(
    val id: UUID,
    val notes: String,
    val exercises: List<Exercise>,
    val isLoading: Boolean,
    val tags: List<Tag>,
    val title: String,
    val isNew: Boolean
)
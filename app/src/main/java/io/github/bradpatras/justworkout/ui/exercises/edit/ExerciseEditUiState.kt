package io.github.bradpatras.justworkout.ui.exercises.edit

import io.github.bradpatras.justworkout.models.Tag
import java.util.UUID

data class ExerciseEditUiState(
    val description: String,
    val tags: List<Tag>,
    val title: String,
    val isNew: Boolean
)

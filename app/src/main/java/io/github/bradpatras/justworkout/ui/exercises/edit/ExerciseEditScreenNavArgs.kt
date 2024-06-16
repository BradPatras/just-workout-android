package io.github.bradpatras.justworkout.ui.exercises.edit

import java.io.Serializable
import java.util.UUID

data class ExerciseEditScreenNavArgs(
    val id: UUID,
    val isNew: Boolean
) : Serializable

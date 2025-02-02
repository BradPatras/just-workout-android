package io.github.bradpatras.justworkout.ui.workouts.edit

import java.io.Serializable
import java.util.UUID

data class WorkoutEditScreenNavArgs(
    val id: UUID,
    val isNew: Boolean
) : Serializable
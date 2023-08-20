package io.github.bradpatras.justworkout.models

import java.io.Serializable
import java.util.Date
import java.util.UUID

data class Workout(
    val datesCompleted: List<Date>,
    val exercises: List<Exercise>,
    val id: UUID,
    val notes: String,
    val title: String
) : Serializable

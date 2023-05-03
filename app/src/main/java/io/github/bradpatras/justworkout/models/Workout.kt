package io.github.bradpatras.justworkout.models

import java.util.Date

data class Workout(
    val datesCompleted: List<Date>,
    val exercises: List<Exercise>,
    val id: Int,
    val notes: String,
    val title: String
)

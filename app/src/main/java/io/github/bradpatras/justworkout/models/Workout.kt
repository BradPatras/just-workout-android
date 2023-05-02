package io.github.bradpatras.justworkout.models

import java.util.Date

data class Workout(
    val title: String,
    val notes: String,
    val exercises: List<Exercise>,
    val datesCompleted: List<Date>
)

package io.github.bradpatras.justworkout.models

data class Exercise(
    val description: String,
    val id: Int,
    val muscleGroups: List<MuscleGroup>,
    val tags: List<Tag>,
    val title: String
)

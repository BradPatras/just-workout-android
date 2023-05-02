package io.github.bradpatras.justworkout.models

data class Exercise(
    val id: Int,
    val title: String,
    val description: String,
    val tags: List<Tag>,
    val muscleGroups: List<MuscleGroup>
)

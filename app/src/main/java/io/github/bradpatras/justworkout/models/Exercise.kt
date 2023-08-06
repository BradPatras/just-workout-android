package io.github.bradpatras.justworkout.models

import java.io.Serializable

data class Exercise(
    val description: String,
    val id: Int,
    val muscleGroups: List<MuscleGroup>,
    val tags: List<Tag>,
    val title: String
) : Serializable

package io.github.bradpatras.justworkout.models

import java.io.Serializable

data class Exercise(
    val description: String,
    val id: Int,
    val tags: List<Tag>,
    val title: String
) : Serializable

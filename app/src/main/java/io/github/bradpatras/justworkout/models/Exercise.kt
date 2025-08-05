package io.github.bradpatras.justworkout.models

import java.io.Serializable
import java.util.UUID

data class Exercise(
    val description: String,
    val id: UUID,
    val tags: List<Tag>,
    val title: String
) : Serializable {
    fun matchesSearchQuery(query: String): Boolean {
        return description.contains(query, ignoreCase = true)
                || title.contains(query, ignoreCase = true)
                || tags.any { it.title.contains(query, ignoreCase = true) }
    }
}

package io.github.bradpatras.justworkout.models

import java.util.UUID

data class SelectableExercise(
    val exercise: Exercise,
    val isSelected: Boolean
) {
    fun title(): String = exercise.title

    fun tags(): List<Tag> = exercise.tags

    fun id(): UUID = exercise.id
}
package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.models.Exercise

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String,
    val tags: List<TagEntity>,
    val title: String
)

fun ExerciseEntity.asExercise() = Exercise(
    description = description,
    id = id,
    tags = tags.map { it.asTag() },
    title = title
)

fun Exercise.asExerciseEntity() = ExerciseEntity(
    id = id,
    description = description,
    tags = tags.map { it.asTagEntity() },
    title = title
)
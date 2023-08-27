package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.models.Exercise

data class ExerciseWithTagsEntity(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "tagId",
        associateBy = Junction(ExerciseTagCrossRef::class)
    )
    val tags: List<TagEntity>
)

fun ExerciseWithTagsEntity.asExercise() = Exercise(
    description = exercise.description,
    id = exercise.exerciseId,
    tags = tags.map { it.asTag() },
    title = exercise.title
)

fun Exercise.asExerciseWithTagsEntity() = ExerciseWithTagsEntity(
    exercise = ExerciseEntity(
        exerciseId = id,
        description = description,
        title = title
    ),
    tags = tags.map { it.asTagEntity() },
)
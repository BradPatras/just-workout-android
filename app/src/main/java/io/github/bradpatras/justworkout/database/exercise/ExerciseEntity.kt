package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.models.Exercise
import java.util.UUID

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey val exerciseId: UUID,
    val description: String,
    val title: String
)

@Entity(primaryKeys = ["exerciseId", "tagId"])
data class ExerciseTagCrossRef(
    val exerciseId: UUID,
    val tagId: UUID
)

data class ExerciseWithTags(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "tagId",
        associateBy = Junction(ExerciseTagCrossRef::class)
    )
    val tags: List<TagEntity>
)

fun ExerciseEntity.asExercise() = Exercise(
    description = description,
    id = exerciseId,
    tags = tags.map { it.asTag() },
    title = title
)

fun Exercise.asExerciseEntity() = ExerciseEntity(
    exerciseId = id,
    description = description,
    tags = tags.map { it.asTagEntity() },
    title = title
)
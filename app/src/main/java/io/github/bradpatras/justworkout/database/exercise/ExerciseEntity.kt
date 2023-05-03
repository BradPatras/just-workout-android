package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupEntity
import io.github.bradpatras.justworkout.database.musclegroup.asMuscleGroup
import io.github.bradpatras.justworkout.database.musclegroup.asMuscleGroupEntity
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.models.Exercise

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String,
    val muscleGroups: List<MuscleGroupEntity>,
    val tags: List<TagEntity>,
    val title: String
)

fun ExerciseEntity.asExercise() = Exercise(
    description = description,
    id = id,
    muscleGroups = muscleGroups.map { it.asMuscleGroup() },
    tags = tags.map { it.asTag() },
    title = title
)

fun Exercise.asExerciseEntity() = ExerciseEntity(
    id = id,
    description = description,
    muscleGroups = muscleGroups.map { it.asMuscleGroupEntity() },
    tags = tags.map { it.asTagEntity() },
    title = title
)
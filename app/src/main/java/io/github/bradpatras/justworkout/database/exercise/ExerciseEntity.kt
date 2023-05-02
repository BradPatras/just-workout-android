package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupEntity
import io.github.bradpatras.justworkout.database.musclegroup.asMuscleGroup
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.models.Exercise

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val tags: List<TagEntity>,
    val muscleGroups: List<MuscleGroupEntity>
)

fun ExerciseEntity.asExercise() = Exercise(
    id = id,
    title = title,
    description = description,
    tags = tags.map { it.asTag() },
    muscleGroups = muscleGroups.map { it.asMuscleGroup() }
)
package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.musclegroup.MuscleGroupEntity
import io.github.bradpatras.justworkout.database.tag.TagEntity

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val tags: List<TagEntity>,
    val muscleGroups: List<MuscleGroupEntity>
)
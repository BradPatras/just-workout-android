package io.github.bradpatras.justworkout.database.musclegroup

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.models.MuscleGroup

@Entity(tableName = "muscle_group")
data class MuscleGroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
)

fun MuscleGroupEntity.asMuscleGroup() = MuscleGroup(
    id = id,
    title = title
)
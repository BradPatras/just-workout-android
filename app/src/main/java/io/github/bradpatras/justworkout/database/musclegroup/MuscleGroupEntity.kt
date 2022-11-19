package io.github.bradpatras.justworkout.database.musclegroup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle_group")
data class MuscleGroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
)
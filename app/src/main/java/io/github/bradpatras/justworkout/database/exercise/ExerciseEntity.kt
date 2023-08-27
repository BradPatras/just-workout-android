package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey val exerciseId: UUID,
    val description: String,
    val title: String
)
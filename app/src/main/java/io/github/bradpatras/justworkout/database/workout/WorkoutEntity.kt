package io.github.bradpatras.justworkout.database.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.exercise.ExerciseEntity

@Entity(tableName = "workout")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val notes: String,
    val exercises: List<ExerciseEntity>,
)
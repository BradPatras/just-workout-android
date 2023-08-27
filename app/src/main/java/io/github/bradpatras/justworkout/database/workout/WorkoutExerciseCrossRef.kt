package io.github.bradpatras.justworkout.database.workout

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "workout_exercise_cross_ref", primaryKeys = ["workoutId", "exerciseId"])
data class WorkoutExerciseCrossRef(
    val workoutId: UUID,
    val exerciseId: UUID
)

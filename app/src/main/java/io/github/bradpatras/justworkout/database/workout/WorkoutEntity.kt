package io.github.bradpatras.justworkout.database.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "workout")
data class WorkoutEntity(
    @PrimaryKey val workoutId: UUID,
    val title: String,
    val notes: String,
    val datesCompleted: List<Date>
)
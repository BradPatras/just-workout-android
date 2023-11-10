package io.github.bradpatras.justworkout.database.workout

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "workout_tag_cross_ref", primaryKeys = ["workoutId", "title"])
data class WorkoutTagCrossRef(
    val workoutId: UUID,
    @ColumnInfo(index = true) val title: String
)
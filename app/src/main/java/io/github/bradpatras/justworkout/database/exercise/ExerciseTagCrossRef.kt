package io.github.bradpatras.justworkout.database.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "exercise_tag_cross_ref", primaryKeys = ["exerciseId", "title"])
data class ExerciseTagCrossRef(
    val exerciseId: UUID,
    @ColumnInfo(index = true) val title: String
)
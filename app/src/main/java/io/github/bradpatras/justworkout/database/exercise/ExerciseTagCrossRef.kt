package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "exercise_tag_cross_ref", primaryKeys = ["exerciseId", "tagId"])
data class ExerciseTagCrossRef(
    val exerciseId: UUID,
    val tagId: UUID
)
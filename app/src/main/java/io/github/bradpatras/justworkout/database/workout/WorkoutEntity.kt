package io.github.bradpatras.justworkout.database.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bradpatras.justworkout.database.exercise.ExerciseEntity
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseEntity
import io.github.bradpatras.justworkout.models.Workout
import java.util.Date

@Entity(tableName = "workout")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val notes: String,
    val exercises: List<ExerciseEntity>,
    val datesCompleted: List<Date>
)

fun WorkoutEntity.asWorkout() = Workout(
    datesCompleted = datesCompleted,
    exercises = exercises.map { it.asExercise() },
    id = id,
    notes = notes,
    title = title
)

fun Workout.asWorkoutEntity() = WorkoutEntity(
    datesCompleted = datesCompleted,
    exercises = exercises.map { it.asExerciseEntity() },
    id = id,
    notes = notes,
    title = title
)
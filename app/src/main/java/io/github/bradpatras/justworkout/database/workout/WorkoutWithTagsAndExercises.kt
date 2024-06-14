package io.github.bradpatras.justworkout.database.workout

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.bradpatras.justworkout.database.exercise.ExerciseEntity
import io.github.bradpatras.justworkout.database.exercise.ExerciseWithTags
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseWithTags
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.tag.asTag
import io.github.bradpatras.justworkout.database.tag.asTagEntity
import io.github.bradpatras.justworkout.models.Workout

data class WorkoutWithTagsAndExercises(
    @Embedded val workout: WorkoutEntity,
    @Relation(
        entity = ExerciseEntity::class, //TODO: investigate if this is correct...
        parentColumn = "workoutId",
        entityColumn = "exerciseId",
        associateBy = Junction(WorkoutExerciseCrossRef::class)
    )
    val exercises: List<ExerciseWithTags>,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "tagId",
        associateBy = Junction(WorkoutTagCrossRef::class)
    )
    val tags: List<TagEntity>
)

fun WorkoutWithTagsAndExercises.asWorkout() = Workout(
    datesCompleted = workout.datesCompleted,
    exercises = exercises.map { it.asExercise() },
    id = workout.workoutId,
    notes = workout.notes,
    tags = tags.map { it.asTag() },
    title = workout.title
)

fun Workout.asWorkoutEntity() = WorkoutWithTagsAndExercises(
    workout = WorkoutEntity(
        datesCompleted = datesCompleted,
        workoutId = id,
        notes = notes,
        title = title
    ),
    exercises = exercises.map { it.asExerciseWithTags() },
    tags = tags.map { it.asTagEntity() }
)

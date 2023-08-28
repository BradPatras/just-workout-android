package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRef
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRefDao
import io.github.bradpatras.justworkout.database.workout.WorkoutTagCrossRef
import io.github.bradpatras.justworkout.database.workout.WorkoutTagCrossRefDao
import io.github.bradpatras.justworkout.database.workout.asWorkout
import io.github.bradpatras.justworkout.database.workout.asWorkoutEntity
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutExerciseCrossRefDao: WorkoutExerciseCrossRefDao,
    private val workoutTagCrossRefDao: WorkoutTagCrossRefDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WorkoutRepository {
    override fun fetchWorkouts(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<List<Workout>> {
        workoutDao
            .getAll()
            .map { it.asWorkout() }
            .also { emit(it) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun updateWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = workout.asWorkoutEntity()
        workoutDao
            .update(workouts = arrayOf(entity.workout))

        workoutExerciseCrossRefDao
            .deleteByWorkout(workoutId = workout.id)
        workoutExerciseCrossRefDao
            .insert(
                entity.exercises.map {
                    WorkoutExerciseCrossRef(workoutId = workout.id, it.exercise.exerciseId)
                }
            )

        workoutTagCrossRefDao
            .deleteByWorkout(workoutId = workout.id)
        workoutTagCrossRefDao
            .insert(
                entity.tags.map {
                    WorkoutTagCrossRef(workoutId = workout.id, it.tagId)
                }
            )

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = workout.asWorkoutEntity()
        workoutDao
            .delete(workout = entity.workout)

        workoutExerciseCrossRefDao.deleteByWorkout(workoutId = workout.id)
        workoutTagCrossRefDao.deleteByWorkout(workoutId = workout.id)

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = workout.asWorkoutEntity()
        workoutDao
            .insert(workouts = arrayOf(entity.workout))

        workoutExerciseCrossRefDao
            .insert(
                entity.exercises.map {
                   WorkoutExerciseCrossRef(
                       workoutId = workout.id,
                       exerciseId = it.exercise.exerciseId
                   )
                }
            )

        workoutTagCrossRefDao
            .insert(
                entity.tags.map {
                    WorkoutTagCrossRef(
                        workoutId = workout.id,
                        tagId = it.tagId
                    )
                }
            )
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}
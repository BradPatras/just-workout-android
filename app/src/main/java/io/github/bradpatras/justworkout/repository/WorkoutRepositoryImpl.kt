package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.asWorkout
import io.github.bradpatras.justworkout.database.workout.asWorkoutEntity
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class WorkoutRepositoryImpl constructor(
    private val workoutDao: WorkoutDao,
    private val ioDispatcher: CoroutineDispatcher
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
        workoutDao
            .update(workouts = arrayOf(workout.asWorkoutEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        workoutDao
            .delete(workout = workout.asWorkoutEntity())
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        workoutDao
            .insert(workouts = arrayOf(workout.asWorkoutEntity()))
            .also { emit(Unit) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}